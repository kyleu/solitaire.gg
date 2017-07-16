package test.utils

import models.settings.Settings
import msg.req.SaveSettings
import utest._
import util.JsonSerializers


object SerializationTests extends TestSuite {
  case class Foo(bar: String = "hello", baz: Int = 0)

  val tests = this{
    'socketRequestMessage {
      val m = SaveSettings(Settings())
      val json = JsonSerializers.writeSocketRequestMessage(m)
      assert(json != "{}")
      assert(JsonSerializers.readSocketRequestMessage(json) == m)
    }
  }
}
