package services

import models.user.{ User, UserFeedback }
import play.api.i18n.Messages
import play.api.libs.mailer._
import utils.Config

class EmailService(mailerClient: MailerClient) {
  def sendWelcomeMessage(toName: String, toAddress: String)(implicit messages: Messages) = {
    val to = toName + " <" + toAddress + ">"
    val htmlTemplate = views.html.email.welcomeHtml()
    val textTemplate = views.html.email.welcomeText()
    val welcomeSubject = Messages("email.welcome.subject")
    sendMessage(to, welcomeSubject, textTemplate.toString(), htmlTemplate.toString())
  }

  def feedbackSubmitted(fb: UserFeedback, user: User)(implicit messages: Messages) = {
    val text = "You should really use HTML mail."
    val html = views.html.email.feedbackHtml(fb, user)
    sendMessage(Config.adminEmail, Config.projectName + " user feedback from [" + fb.userId + "]", text, html.toString())
  }

  def sendMessage(to: String, subject: String, textMessage: String, htmlMessage: String)(implicit messages: Messages) = {
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
    mailerClient.send(email)
  }
}
