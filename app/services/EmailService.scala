package services

import models.audit.DailyMetric
import models.user.{ User, UserFeedback }
import org.joda.time.LocalDate
import play.api.i18n.Messages
import play.api.libs.mailer._
import utils.Config

@javax.inject.Singleton
class EmailService @javax.inject.Inject() (mailerClient: MailerClient) {
  def sendWelcomeMessage(toName: String, toAddress: String)(implicit messages: Messages) = {
    val to = s"$toName <$toAddress>"
    val textTemplate = views.html.email.welcomeText()
    val htmlTemplate = views.html.email.welcomeHtml().toString()
    val welcomeSubject = Messages("email.welcome.subject")
    sendMessage(Messages("email.from"), to, welcomeSubject, textTemplate.toString(), htmlTemplate)
  }

  def feedbackSubmitted(fb: UserFeedback, user: User)(implicit messages: Messages) = {
    val text = "You should really use HTML mail."
    val html = views.html.email.feedbackHtml(fb, user).toString
    sendMessage(Messages("email.from"), Config.adminEmail, s"${Config.projectName} user feedback from [${fb.userId}]", text, html)
  }

  def sendDailyReport(d: LocalDate, color: String, metrics: Map[DailyMetric.Metric, Long], tableCounts: Seq[(String, Long)]) = {
    val text = "You should really use HTML mail."
    val html = views.html.admin.report.emailReport(d, color, metrics, tableCounts).toString
    val from = "Solitaire.gg <solitaire@solitaire.gg>"
    sendMessage(from, Config.adminEmail, s"${Config.projectName} report for [$d]", text, html)
  }

  def sendMessage(from: String, to: String, subject: String, textMessage: String, htmlMessage: String) = {
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
