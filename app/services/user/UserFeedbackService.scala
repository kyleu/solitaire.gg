package services.user

import java.util.UUID

import models.database.queries.UserFeedbackQueries
import models.user.UserFeedback
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object UserFeedbackService {
  def save(feedback: UserFeedback) = {
    Database.execute(UserFeedbackQueries.CreateUserFeedback(feedback)).map(_ == 1)
  }

  def searchFeedback(q: String, orderBy: String) = Database.query(UserFeedbackQueries.SearchUserFeedback(q, orderBy))

  def getByUser(id: UUID, sortBy: String) = Database.query(UserFeedbackQueries.GetUserFeedbackByUser(id, sortBy))
}
