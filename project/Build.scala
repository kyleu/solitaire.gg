import sbt._
import com.typesafe.sbt.packager.universal.UniversalKeys

object Build extends Build with UniversalKeys {
  lazy val shared = Shared.shared
  lazy val sharedClasses = Shared.sharedClasses
  lazy val sharedJvm = Shared.sharedJvm
  lazy val sharedJs = Shared.sharedJs
  lazy val client = Client.client
  lazy val server = Server.server
}
