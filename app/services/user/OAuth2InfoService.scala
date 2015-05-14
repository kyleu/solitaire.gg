package services.user

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import com.mohiva.play.silhouette.impl.providers.OAuth2Info
import models.database.queries.auth.OAuth2InfoQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object OAuth2InfoService extends DelegableAuthInfoDAO[OAuth2Info] {
  override def save(loginInfo: LoginInfo, authInfo: OAuth2Info) = Database.execute(OAuth2InfoQueries.CreateOAuth2Info(loginInfo, authInfo)).map(x => authInfo)
  override def find(loginInfo: LoginInfo) = Database.query(OAuth2InfoQueries.FindOAuth2Info(loginInfo))
}
