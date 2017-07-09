package models.graphql

import models.history.{GameHistorySchema, GameSeedSchema}
import models.sandbox.SandboxSchema
import models.test.TestSchema
import models.user.UserSchema
import sangria.execution.deferred.DeferredResolver
import sangria.schema._

object Schema {
  val resolver: DeferredResolver[GraphQLContext] = DeferredResolver.fetchers(
    models.history.GameHistorySchema.gameHistoryFetcher,
    models.user.UserSchema.userFetcherById
  )

  val queryFields = SandboxSchema.queryFields ++ UserSchema.queryFields ++ GameHistorySchema.queryFields ++ GameSeedSchema.queryFields

  val queryType = ObjectType(
    name = "Query",
    description = "The main query interface.",
    fields = queryFields
  )

  val mutationFields = SandboxSchema.mutationFields ++ GameHistorySchema.mutationFields ++ TestSchema.mutationFields

  val mutationType = ObjectType(
    name = "Mutation",
    description = "The main mutation interface.",
    fields = mutationFields
  )

  val schema = sangria.schema.Schema(
    query = queryType,
    mutation = Some(mutationType)
  )
}
