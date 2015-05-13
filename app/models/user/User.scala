package models.user

import java.util.UUID

import com.mohiva.play.silhouette.api.{ Identity, LoginInfo }

case class User(
  id: UUID,
  loginInfo: LoginInfo,
  socials: Option[Seq[LoginInfo]] = None,
  username: Option[String],
  email: Option[String],
  avatarUrl: Option[String],
  info: BaseInfo,
  roles: Set[Role] = Set(Role.User)
) extends Identity
