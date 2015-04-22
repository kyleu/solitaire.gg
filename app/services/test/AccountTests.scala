package services.test

import models.Account
import models.test.{ Tree, Test }
import services.AccountService

class AccountTests {
  private[this] val testUser = "test-account"
  private[this] var account: Account = _

  private[this] def create() = Test("create", () => {
    account = AccountService.createAccount(testUser)
    assert(account.name == "test-account")
  })

  private[this] def updateName() = Test("update-name", () => {
    AccountService.updateAccountName(account.id, "test-account-new-name")
    val newName = AccountService.getAccount(account.id).map(_.name).getOrElse("missing")
    assert(newName == "test-account-new-name")
  })

  private[this] def remove() = Test("remove", () => {
    AccountService.removeAccount(account.id)
    val missing = AccountService.getAccount(account.id)
    assert(missing == None)
  })

  val all = {
    Tree(Test("account"), Seq(create().toTree, updateName().toTree, remove().toTree))
  }
}
