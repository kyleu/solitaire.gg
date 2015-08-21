package services.email

import models.audit.{ DailyMetric, UserFeedback }
import models.history.GameHistory
import models.user.User
import org.joda.time.LocalDate
import play.api.i18n.Messages
import play.api.libs.mailer._
import services.audit.DailyMetricService
import utils.{ Config, DateUtils }

@javax.inject.Singleton
class EmailService @javax.inject.Inject() (mailerClient: MailerClient, config: Config) {
  private[this] val adminFrom = "Solitaire.gg <solitaire@solitaire.gg>"
  private[this] val adminTextMessage = "You should really use HTML mail."

  def sendWelcomeMessage(toName: String, toAddress: String)(implicit messages: Messages) = {
    val to = s"$toName <$toAddress>"
    val textTemplate = views.html.email.welcomeText()
    val htmlTemplate = views.html.email.welcomeHtml().toString()
    val welcomeSubject = Messages("email.welcome.subject", Config.projectName)
    sendMessage(Messages("email.from"), to, welcomeSubject, textTemplate.toString(), htmlTemplate)
  }

  def feedbackSubmitted(fb: UserFeedback, user: User)(implicit messages: Messages) = {
    val text = "You should really use HTML mail."
    val html = views.html.email.feedbackHtml(fb, user).toString
    sendMessage(Messages("email.from"), config.adminEmail, s"${Config.projectName} user feedback from [${fb.userId}]", text, html)
  }

  def sendDailyReport(
    d: LocalDate,
    color: String,
    metrics: Map[DailyMetric.Metric, Long],
    totals: Map[DailyMetric.Metric, Long],
    wins: Seq[(GameHistory, User)],
    tableCounts: Seq[(String, Long)]
  ) = {
    val html = views.html.admin.report.emailReport(d, color, metrics, totals, wins, tableCounts).toString
    sendMessage(adminFrom, config.adminEmail, s"${Config.projectName} report for [$d]", adminTextMessage, html)
    DailyMetricService.setMetric(d, DailyMetric.ReportSent, 1L)
  }

  def sendError(msg: String, ctx: String, ex: Option[Throwable], user: Option[User]) = {
    val html = views.html.email.severeErrorHtml(msg, ctx, ex, user, DateUtils.now).toString
    sendMessage(adminFrom, config.adminEmail, s"${Config.projectName} error for [$ctx].", adminTextMessage, html)
  }

  def sendMessage(from: String, to: String, subject: String, textMessage: String, htmlMessage: String) = {
    val email = Email(
      subject = subject,
      from = from,
      to = Seq(to),
      bodyText = Some(textMessage),
      bodyHtml = Some(htmlMessage),
      cc = Nil,
      bcc = Nil,
      attachments = Nil
    )
    mailerClient.send(email)
  }
}