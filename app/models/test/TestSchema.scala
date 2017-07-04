package models.test

import models.graphql.GraphQLContext
import sangria.macros.derive.{DocumentField, IncludeMethods, deriveObjectType}
import sangria.schema.{Field, fields}

import scala.util.Random

object TestSchema {
  private[this] class TestApi(ctx: GraphQLContext) {
    def solve(rules: String, seed: Option[Int]) = {
      val seedToUse = seed.getOrElse(Random.nextInt(1000000))
      s"Solving [$rules:$seedToUse]..."
    }
  }

  private[this] val mutationType = deriveObjectType[GraphQLContext, TestApi](
    IncludeMethods("solve"),
    DocumentField("solve", "Attempts to solve the provided game rules, returning the results.")
  )

  val mutationFields = fields[GraphQLContext, Unit](
    Field(
      name = "test",
      fieldType = mutationType,
      description = Some("Allows calling of system tests."),
      resolve = c => new TestApi(c.ctx)
    )
  )
}
