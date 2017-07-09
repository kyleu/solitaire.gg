package models.history

import java.util.UUID

import models.graphql.{CommonSchema, GraphQLContext}
import models.graphql.CommonSchema.uuidType
import models.graphql.DateTimeSchema.localDateTimeType
import models.user.UserSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.macros.derive._
import sangria.schema._
import services.history.{GameHistoryService, GameSeedService}

object GameHistorySchema {
  implicit val gameStatusEnum = CommonSchema.deriveStringEnumeratumType(name = "GameStatus", values = GameHistory.Status.values)

  implicit val gameHistoryType: OutputType[GameHistory] = deriveObjectType[GraphQLContext, GameHistory](
    ObjectTypeDescription("An instance of a game, played at some point."),
    RenameField("player", "playerId"),
    RenameField("seed", "seedId"),
    AddFields(
      Field(
        name = "seed",
        fieldType = OptionType(GameSeedSchema.gameSeedType),
        description = Some("The seed record associated with this game."),
        resolve = ctx => GameSeedService.getGameSeed(ctx.value.rules, ctx.value.seed)
      ),
      Field(
        name = "player",
        fieldType = OptionType(UserSchema.userType),
        description = Some("The player responsible for this game."),
        resolve = ctx => UserSchema.userFetcherById.defer(ctx.value.player)
      )
    )
  )

  implicit val gameHistoryId = HasId[GameHistory, UUID](_.id)
  val gameHistoryByPlayer = Relation[GameHistory, UUID]("byPlayer", h => Seq(h.player))

  val gameHistoryFetcher = Fetcher.rel[GraphQLContext, GameHistory, GameHistory, UUID](
    (_, ids) => GameHistoryService.getByIds(ids),
    (_, rels) => { GameHistoryService.getByUsers(rels(gameHistoryByPlayer)) }
  )

  val queryFields = fields[GraphQLContext, Unit](Field(
    name = "games",
    description = Some("Returns a list of games."),
    arguments = CommonSchema.queryArg :: CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
    fieldType = ListType(gameHistoryType),
    resolve = c => c.arg(CommonSchema.queryArg) match {
      case Some(q) => GameHistoryService.search(q, c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
      case _ => GameHistoryService.getAll(c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
    }
  ))

  class GameHistoryApi() {
    def remove(id: Option[UUID], email: Option[String]) = "TODO"
  }

  val mutationType = deriveObjectType[GraphQLContext, GameHistoryApi](
    IncludeMethods("remove"),
    DocumentField("remove", "Removes a user from the system.")
  )

  val mutationFields = fields[GraphQLContext, Unit](Field(
    name = "games",
    fieldType = mutationType,
    description = Some("Allows mutation and removal of recorded games."),
    resolve = _ => new GameHistoryApi()
  ))
}
