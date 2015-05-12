package services

import play.api.libs.mailer._

object EmailService {
  private[this] val from = "Solitare.gg <solitaire@solitaire.gg>"
  private[this] val welcomeSubject = "Welcome to Solitaire.gg!"

  def sendWelcomeMessage(toName: String, toAddress: String) = {
    val to = toName + " <" + toAddress + ">"
    val htmlTemplate = views.html.email.welcomeHtml()
    val textTemplate = views.html.email.welcomeText()
    sendMessage(to, welcomeSubject, textTemplate.toString(), htmlTemplate.toString())
  }

  private[this] def sendMessage(to: String, subject: String, textMessage: String, htmlMessage: String) = {
    import play.api.Play.current
    val email = Email(
      subject = subject,
      from = from,
      to = Seq(to),
      bodyText = Some(textMessage),
      bodyHtml = Some(htmlMessage),
      cc = Nil,
      bcc = Nil,
      attachments = Nil
    )
    MailerPlugin.send(email)
  }
}
