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

  def getAccount(map: Map[String, String], fallbackAccount: Option[UUID]) = {
    if (map.get("account").isDefined && map.get("name").isDefined) {
      val accountId = UUID.fromString(map("account"))
      (accountId, map("name"), false)
    } else {
      fallbackAccount match {
        case Some(acctId) =>
          DatabaseConnection.query(GetAccount(acctId)) match {
            case Some(a) =>
              (a.id , a.name,true)
            case None =>
              val name = "guest-" + Math.abs(Random.nextInt(100000))
              val a = AccountService.createAccount(name)
              (a.id , a.name, true)
          }
        case None =>
          val name = "guest-" + Math.abs(Random.nextInt(100000))
          val a = AccountService.createAccount(name)
          (a.id , a.name, true)
      }
    }
  }

  def updateAccountName(id: UUID, name: String) = DatabaseConnection.transaction {
    DatabaseConnection.execute(UpdateAccountName(id, name)) == 1
  }

  def removeAccount(id: UUID) = DatabaseConnection.transaction {
    DatabaseConnection.execute(RemoveAccount(id)) == 1
  }
}
