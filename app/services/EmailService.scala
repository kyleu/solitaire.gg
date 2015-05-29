package services

import models.user.{ User, UserFeedback }
import play.api.i18n.{ Messages, Lang }
import play.api.libs.mailer._
import utils.Config

object EmailService {
  def sendWelcomeMessage(toName: String, toAddress: String)(implicit lang: Lang) = {
    val to = toName + " <" + toAddress + ">"
    val htmlTemplate = views.html.email.welcomeHtml()
    val textTemplate = views.html.email.welcomeText()
    val welcomeSubject = Messages("email.welcome.subject")
    sendMessage(to, welcomeSubject, textTemplate.toString(), htmlTemplate.toString())
  }

  def feedbackSubmitted(fb: UserFeedback, user: User) = {
    val text = "You should really use HTML mail."
    val html = views.html.email.feedbackHtml(fb, user)
    sendMessage(Config.adminEmail, Config.projectName + " user feedback from [" + fb.userId + "]", text, html.toString)
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
