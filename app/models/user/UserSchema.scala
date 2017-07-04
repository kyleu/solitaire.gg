package models.user

import models.graphql.{CommonSchema, GraphQLContext}
import models.graphql.CommonSchema.uuidType
import models.graphql.DateTimeSchema.localDateTimeType
import models.settings.SettingsSchema._
import models.history.GameHistorySchema._
import sangria.macros.derive._
import sangria.schema._
import services.history.GameHistoryService
import services.user.UserService

object UserSchema {
  implicit val userType: OutputType[User] = deriveObjectType[GraphQLContext, User](
    ObjectTypeDescription("A user of the system."),
    AddFields(
      Field(
        name = "gameCount",
        fieldType = IntType,
        description = Some("Count of games played by this user"),
        resolve = ctx => GameHistoryService.getCountByUser(ctx.value.id)
      ),
      Field(
        name = "games",
        fieldType = ListType(gameHistoryType),
        arguments = CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
        description = Some("Games played by this user"),
        resolve = ctx => GameHistoryService.getByUser(ctx.value.id, ctx.arg(CommonSchema.limitArg), ctx.arg(CommonSchema.offsetArg))
      )
    )
  )

  val queryFields = fields[GraphQLContext, Unit](Field(
    name = "users",
    description = Some("Returns a list of users."),
    arguments = CommonSchema.queryArg :: CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
    fieldType = ListType(userType),
    resolve = c => c.arg(CommonSchema.queryArg) match {
      case Some(q) => UserService.search(q, c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
      case _ => UserService.getAll(c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
    }
  ))
}
