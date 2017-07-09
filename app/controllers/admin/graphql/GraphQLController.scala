package controllers.admin.graphql

import controllers.BaseController
import io.circe.Json
import models.user.User
import play.api.libs.json.JsObject
import sangria.execution.{ErrorWithResolver, QueryAnalysisError}
import sangria.marshalling.circe._
import sangria.marshalling.MarshallingUtil._
import sangria.parser.SyntaxError
import services.graphql.GraphQLService
import utils.{Application, FutureUtils}
import utils.FutureUtils.defaultContext

import scala.concurrent.Future

@javax.inject.Singleton
class GraphQLController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def graphql(query: Option[String], variables: Option[String]) = {
    withAdminSession("graphql.ui") { implicit request =>
      getUser(request).map { user =>
        Ok(views.html.admin.graphql.graphiql(user))
      }
    }
  }

  def graphqlBody = withAdminSession("graphql.post") { implicit request =>
    val json = {
      import sangria.marshalling.playJson._
      val playJson = request.body.asJson.getOrElse(JsObject.empty)
      playJson.convertMarshaled[Json]
    }
    val body = json.asObject.get.filter(x => x._1 != "variables").toMap
    val query = body("query").as[String].getOrElse("{}")
    val operation = body.get("operationName").flatMap(_.asString)

    val variables = body.get("variables").map { x =>
      x.asString.map(GraphQLService.parseVariables).getOrElse(x)
    }

    getUser(request).flatMap { user =>
      executeQuery(query, variables, operation, user)
    }
  }

  def executeQuery(query: String, variables: Option[Json], operation: Option[String], user: User) = {
    try {
      val f = GraphQLService.executeQuery(app, query, variables, operation, user)
      f.map(x => Ok(x.spaces2).as("application/json"))(FutureUtils.defaultContext).recover {
        case error: QueryAnalysisError => BadRequest(error.resolveError.spaces2).as("application/json")
        case error: ErrorWithResolver => InternalServerError(error.resolveError.spaces2).as("application/json")
      }(FutureUtils.defaultContext)
    } catch {
      case error: SyntaxError =>
        val json = Json.obj(
          "syntaxError" -> Json.fromString(error.getMessage),
          "locations" -> Json.arr(Json.obj(
            "line" -> Json.fromInt(error.originalError.position.line),
            "column" -> Json.fromInt(error.originalError.position.column)
          ))
        )
        Future.successful(BadRequest(json.spaces2).as("application/json"))
    }
  }
}
