package models.user

case class RegistrationData(
  username: String,
  email: String,
  firstName: String,
  lastName: String,
  password: String
)
