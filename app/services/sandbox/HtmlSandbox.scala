package services.sandbox

import utils.Application

object HtmlSandbox extends SandboxTask {
  override def id = "html-sandbox"
  override def description = "Show the rendered contents of sandbox.scala.html"
  override def run(ctx: Application) = throw new IllegalStateException("Not meant to be run directly.")
}
