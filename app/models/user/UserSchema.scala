package models.user

import models.graphql.{CommonSchema, GraphQLContext}
import models.graphql.CommonSchema.uuidType
import models.graphql.DateTimeSchema.localDateTimeType
import models.settings.SettingsSchema._
import sangria.macros.derive._
import sangria.schema._
import services.user.UserService

object UserSchema {
  implicit val userType: OutputType[User] = deriveObjectType[GraphQLContext, User](
    ObjectTypeDescription("A user of the system.")
  )

  val queryFields = fields[GraphQLContext, Unit](Field(
    name = "user",
    description = Some("Returns a list of users."),
    arguments = CommonSchema.queryArg :: CommonSchema.limitArg :: CommonSchema.offsetArg :: Nil,
    fieldType = ListType(userType),
    resolve = c => c.arg(CommonSchema.queryArg) match {
      case Some(q) => UserService.search(q, c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
      case _ => UserService.getAll(c.arg(CommonSchema.limitArg), c.arg(CommonSchema.offsetArg))
    }
  ))

  class UserApi() {
    def remove(id: Option[Int], email: Option[String]) = "TODO"
  }

  val mutationType = deriveObjectType[GraphQLContext, UserApi](
    IncludeMethods("remove"),
    DocumentField("remove", "Removes a user from the system.")
  )

  val mutationFields = fields[GraphQLContext, Unit](Field(
    name = "user",
    fieldType = mutationType,
    description = Some("Allows mutation and removal of system users."),
    resolve = _ => new UserApi()
  ))

}
