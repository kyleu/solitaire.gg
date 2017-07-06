package models.history

import models.graphql.{CommonSchema, GraphQLContext}
import models.graphql.CommonSchema.uuidType
import models.graphql.DateTimeSchema.localDateTimeType
import sangria.macros.derive._
import sangria.schema._
import services.history.{GameHistoryService, GameSeedService}

object GameSeedSchema {
  implicit val gameSeedRecordType = deriveObjectType[GraphQLContext, GameSeed.Record](
    ObjectTypeName("GameSeedRecord")
  )

  implicit val gameSeedType = deriveObjectType[GraphQLContext, GameSeed](
    ObjectTypeDescription("The history for a particular game rule's seed."),
    AddFields(Field(
      name = "histories",
      fieldType = ListType(GameHistorySchema.gameHistoryType),
      arguments = CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
      description = Some("Games played using this rule's seed."),
      resolve = ctx => GameHistoryService.getBySeed(ctx.value.rules, ctx.value.seed, ctx.arg(CommonSchema.limitArg), ctx.arg(CommonSchema.offsetArg))
    ))
  )

  val queryFields = fields[GraphQLContext, Unit](Field(
    name = "seeds",
    description = Some("Returns a list of game seeds."),
    arguments = CommonSchema.queryArg :: CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
    fieldType = ListType(gameSeedType),
    resolve = c => c.arg(CommonSchema.queryArg) match {
      case Some(q) => GameSeedService.search(q, c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
      case _ => GameSeedService.getAll(c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
    }
  ))
}
