package services.account

import java.util.UUID

import models.database.queries.AccountQueries._
import org.joda.time.LocalDateTime
import services.database.Database

import scala.concurrent.Future
import scala.util.Random
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object AccountService {
  def createAccount(name: String) = Database.transaction { c =>
    Database.execute(CreateAccount(name), c).flatMap { i =>
      Database.query(GetAccountByName(name), c).map(_.getOrElse(throw new IllegalStateException("Invalid account.")))
    }
  }

  def searchAccounts(q: String) = Database.query(SearchAccounts(q))

  def getAccount(id: UUID) = Database.query(GetAccount(id))

  def getAccount(map: Map[String, String], fallbackAccount: Option[UUID]): Future[(UUID, String, String, Boolean)] = {
    if (map.get("account").isDefined && map.get("name").isDefined && map.get("role").isDefined) {
      val accountId = UUID.fromString(map("account"))
      Future.successful((accountId, map("name"), map("role"), false))
    } else {
      fallbackAccount match {
        case Some(acctId) =>
          Database.query(GetAccount(acctId)).flatMap {
            case Some(a) =>
              Future.successful((a.id, a.name, a.role, true))
            case None =>
              val name = "Guest " + Math.abs(Random.nextInt(100000))
              AccountService.createAccount(name).map { a =>
                (a.id, a.name, a.role, true)
              }
          }
        case None =>
          val name = "Guest " + Math.abs(Random.nextInt(100000))
          AccountService.createAccount(name).map { a =>
            (a.id, a.name, a.role, true)
          }
      }
    }
  }

  def updateAccountName(id: UUID, name: String) = Database.execute(UpdateAccountName(id, name)).map(_ == 1)

  def updateAccountRole(id: UUID, role: String) = Database.execute(UpdateAccountRole(id, role)).map(_ == 1)

  def incrementAccountGamesPlayed(id: UUID, started: LocalDateTime) = Database.execute(IncrementAccountGamesPlayed(id, started)).map(_ == 1)

  def removeAccount(id: UUID) = Database.execute(RemoveAccount(id)).map(_ == 1)
}
