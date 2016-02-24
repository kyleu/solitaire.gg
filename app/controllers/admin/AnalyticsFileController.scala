package controllers.admin

import com.twitter.util.NonFatal
import controllers.BaseController
import controllers.admin.AnalyticsFileController.Detail
import models.analytics._
import models.audit.AnalyticsEvent
import org.joda.time.LocalDate
import play.api.libs.json._
import services.analytics.AnalyticsFileService
import utils.ApplicationContext

import scala.concurrent.Future

object AnalyticsFileController {
  case class Detail(
    clientEvent: Option[models.analytics.ClientEvent],
    analyticsEvent: Option[models.audit.AnalyticsEvent],
    line: String,
    throwable: Option[Throwable],
    jsError: Option[play.api.libs.json.JsError]
  )
}

@javax.inject.Singleton
class AnalyticsFileController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  val fileService = new AnalyticsFileService(ctx.config)

  def overview() = withAdminSession("overview") { implicit request =>
    import utils.DateUtils._
    Future.successful {
      val ret = fileService.getFileSystemDetails.map { fsd =>
        fsd._1 -> fileService.lineCounts(fsd._1)
      }.sortBy(_._1)
      Ok(views.html.admin.analytics.fileOverview(ret))
    }
  }

  def trends(key: String) = withAdminSession("trends") { implicit request =>
    Future.successful {
      Ok(views.html.admin.analytics.fileTrends(key))
    }
  }

  def summary(d: LocalDate) = withAdminSession("summary") { implicit request =>
    Future.successful {
      Ok(views.html.admin.analytics.fileDailySummary(d))
    }
  }

  def detail(d: LocalDate, key: String) = withAdminSession("detail") { implicit request =>
    import utils.json.AdminSerializers._

    Future.successful {
      val lines = fileService.getLines(d, key).toSeq
      val events = lines.map { line =>
        try {
          val json = Json.parse(line)
          val parseResult = Json.fromJson[AnalyticsEvent](json)
          parseResult match {
            case s: JsSuccess[AnalyticsEvent] => try {
              val fixedTimestamp = (s.value.data \ "occurred").asOpt[String].map(_.toLong).getOrElse((s.value.data \ "occurred").asOpt[Long].getOrElse(0L))
              val fixedData = s.value.data.asOpt[JsObject].map { o =>
                o + ("occurred" -> JsNumber(BigDecimal(fixedTimestamp)))
              }.getOrElse(s.value.data)
              val clientEventResult = s.value.eventType match {
                case AnalyticsEvent.EventType.Error => Json.fromJson[ErrorEvent](fixedData)
                case AnalyticsEvent.EventType.Install => Json.fromJson[InstallEvent](fixedData)
                case AnalyticsEvent.EventType.Open => Json.fromJson[OpenEvent](fixedData)
                case AnalyticsEvent.EventType.GameStart => Json.fromJson[GameStartEvent](fixedData)
                case AnalyticsEvent.EventType.GameWon => Json.fromJson[GameWonEvent](fixedData)
                case AnalyticsEvent.EventType.GameResigned => Json.fromJson[GameResignedEvent](fixedData)
                case u: AnalyticsEvent.EventType.Unknown => throw new IllegalStateException(u.id)
                case _ => throw new IllegalStateException("TODO")
              }
              clientEventResult match {
                case JsSuccess(ce, _) => Detail(Some(ce), Some(s.value), line, None, None)
                case e: JsError => Detail(None, Some(s.value), line, None, Some(e))
              }
            } catch {
              case NonFatal(x) => Detail(None, Some(s.value), line, Some(x), None)
            }
            case e: JsError => Detail(None, None, line, None, Some(e))
          }
        } catch {
          case NonFatal(x) => Detail(None, None, line, Some(x), None)
        }
      }
      Ok(views.html.admin.analytics.fileDetail(d, key, events))
    }
  }
}
