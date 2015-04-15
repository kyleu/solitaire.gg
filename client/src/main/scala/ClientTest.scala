import scala.scalajs.js
import org.scalajs.dom

object ClientTest extends js.JSApp {
  def main(): Unit = {
    dom.document.getElementById("client-test").textContent = "Somehow, it works!"
  }
}
