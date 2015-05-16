package services.user

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import com.mohiva.play.silhouette.impl.providers.OpenIDInfo
import models.database.queries.auth.OpenIdInfoQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object OpenIdInfoService extends DelegableAuthInfoDAO[OpenIDInfo] {
  override def save(loginInfo: LoginInfo, authInfo: OpenIDInfo) = {
    Database.execute(OpenIdInfoQueries.CreateOpenIdInfo(loginInfo, authInfo)).map(x => authInfo)
  }

  override def find(loginInfo: LoginInfo) = {
    Database.query(OpenIdInfoQueries.FindOpenIdInfo(loginInfo))
  }
}
