package models.user

import java.util.UUID

import com.mohiva.play.silhouette.api.{ Identity, LoginInfo }

case class User(
  id: UUID,
  loginInfos: Seq[LoginInfo],
  username: Option[String],
  email: Option[String],
  avatarUrl: Option[String],
  firstName: Option[String],
  lastName: Option[String],
  fullName: Option[String],
  gender: Option[String],
  roles: Set[Role] = Set(Role.User)
) extends Identity
