package models.graphql

import models.history.GameHistorySchema
import models.sandbox.SandboxSchema
import models.user.UserSchema
import sangria.schema._

object Schema {
  val queryFields = SandboxSchema.queryFields ++ UserSchema.queryFields ++ GameHistorySchema.queryFields

  val queryType = ObjectType(
    name = "Query",
    description = "The main query interface.",
    fields = queryFields
  )

  val mutationFields = SandboxSchema.mutationFields ++ GameHistorySchema.mutationFields

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
