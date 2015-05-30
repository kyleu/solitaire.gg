package services.user

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import models.database.queries.auth.PasswordInfoQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

object PasswordInfoService extends DelegableAuthInfoDAO[PasswordInfo] {
  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = {
    Database.transaction { conn =>
      Database.execute(PasswordInfoQueries.UpdatePasswordInfo(loginInfo, authInfo), conn).flatMap { rowsAffected =>
        if (rowsAffected == 0) {
          Database.execute(PasswordInfoQueries.CreatePasswordInfo(loginInfo, authInfo), conn).map(x => authInfo)
        } else {
          Future.successful(authInfo)
        }
      }
    }
  }

  override def find(loginInfo: LoginInfo) = Database.query(PasswordInfoQueries.GetById(Seq(loginInfo.providerID, loginInfo.providerKey)))
}
