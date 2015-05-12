package services.user


import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OAuth1Info
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import scala.collection.mutable
import scala.concurrent.Future

object OAuth1InfoService extends DelegableAuthInfoDAO[OAuth1Info] {
  def save(loginInfo: LoginInfo, authInfo: OAuth1Info): Future[OAuth1Info] = {
    data += (loginInfo -> authInfo)
    Future.successful(authInfo)
  }

  def find(loginInfo: LoginInfo): Future[Option[OAuth1Info]] = {
    Future.successful(data.get(loginInfo))
  }

  var data: mutable.HashMap[LoginInfo, OAuth1Info] = mutable.HashMap()
}
