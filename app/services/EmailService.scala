package services

import play.api.i18n.{ Messages, Lang }
import play.api.libs.mailer._

object EmailService {
  def sendWelcomeMessage(toName: String, toAddress: String)(implicit lang: Lang) = {
    val to = toName + " <" + toAddress + ">"
    val htmlTemplate = views.html.email.welcomeHtml()
    val textTemplate = views.html.email.welcomeText()
    val welcomeSubject = Messages("email.welcome.subject")
    sendMessage(to, welcomeSubject, textTemplate.toString(), htmlTemplate.toString())
  }

  private[this] def sendMessage(to: String, subject: String, textMessage: String, htmlMessage: String)(implicit lang: Lang) = {
    import play.api.Play.current
    val from = Messages("email.from")

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
