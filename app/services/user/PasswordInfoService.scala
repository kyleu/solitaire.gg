package services.user

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import models.database.queries.auth.PasswordInfoQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object PasswordInfoService extends DelegableAuthInfoDAO[PasswordInfo] {
  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = Database.execute(PasswordInfoQueries.CreatePasswordInfo(loginInfo, authInfo)).map(x => authInfo)
  override def find(loginInfo: LoginInfo) = Database.query(PasswordInfoQueries.FindPasswordInfo(loginInfo))
}
