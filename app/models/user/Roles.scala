package models.user

import com.mohiva.play.silhouette.api.Authorization
import play.api.i18n._
import play.api.mvc.RequestHeader

case class WithRole(role: Role) extends Authorization[User] {
  def isAuthorized(user: User)(implicit request: RequestHeader, lang: Lang) = user.roles match {
    case list: Set[Role] => list.contains(role)
    case _ => false
  }
}

sealed trait Role {
  def name: String
}

object Role {
  def apply(role: String): Role = role match {
    case Admin.name => Admin
    case User.name => User
    case _ => Unknown
  }

  def unapply(role: Role): Option[String] = Some(role.name)

  object Admin extends Role {
    val name = "admin"
  }

  object User extends Role {
    val name = "user"
  }

  object Unknown extends Role {
    val name = "-"
  }
}
