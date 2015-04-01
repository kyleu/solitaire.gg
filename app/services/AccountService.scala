package services

import java.util.UUID

import models.queries.AccountQueries._
import services.database.DatabaseConnection

import scala.util.Random

object AccountService {
  def createAccount(name: String) = DatabaseConnection.transaction {
    DatabaseConnection.execute(CreateAccount(name))
    DatabaseConnection.query(GetAccountByName(name)).getOrElse(throw new IllegalStateException("Invalid account."))
  }

  def getAccount(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.query(GetAccount(id))
  }

  def getAccount(map: Map[String, String]) = {
    if (map.get("account").isDefined && map.get("name").isDefined) {
      UUID.fromString(map("account")) -> map("name")
    } else {
      val name = "guest-" + Math.abs(Random.nextInt(100000))
      val a = AccountService.createAccount(name)
      a.id -> a.name
    }
  }

  def getAccountByName(name: String) = DatabaseConnection.transaction {
    DatabaseConnection.query(GetAccountByName(name))
  }

  def updateAccountName(id: UUID, name: String) = DatabaseConnection.transaction {
    DatabaseConnection.execute(UpdateAccountName(id, name)) == 1
  }

  def removeAccount(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.execute(RemoveAccount(id)) == 1
  }
}
