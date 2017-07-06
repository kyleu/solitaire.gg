package models.user

import models.graphql.{CommonSchema, GraphQLContext}
import models.graphql.CommonSchema.uuidType
import models.graphql.DateTimeSchema.localDateTimeType
import models.settings.SettingsSchema.settingsType
import models.history.GameHistorySchema
import sangria.macros.derive._
import sangria.schema._
import services.history.GameHistoryService
import services.user.{UserService, UserStatisticsService}

object UserSchema {
  implicit val userStatisticsGamesType = deriveObjectType[GraphQLContext, UserStatistics.Games](
    ObjectTypeName("GameStatistics")
  )
  implicit val userStatisticsType = deriveObjectType[GraphQLContext, UserStatistics]()

  implicit val userType = deriveObjectType[GraphQLContext, User](
    ObjectTypeDescription("A user of the system."),
    AddFields(
      Field(
        name = "statistics",
        fieldType = userStatisticsType,
        description = Some("User statistics, mostly about games played."),
        resolve = ctx => UserStatisticsService.getStatistics(ctx.value.id)
      ),
      Field(
        name = "gameCount",
        fieldType = IntType,
        description = Some("Count of games played by this user."),
        resolve = ctx => GameHistoryService.getCountByUser(ctx.value.id)
      ),
      Field(
        name = "games",
        fieldType = ListType(GameHistorySchema.gameHistoryType),
        arguments = CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
        description = Some("Games played by this user."),
        resolve = ctx => GameHistorySchema.gameHistoryFetcher.deferRelSeq(GameHistorySchema.gameHistoryByPlayer, ctx.value.id)
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
