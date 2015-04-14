package services.test

import java.util.UUID

import models.Account
import org.joda.time.LocalDateTime
import services.AccountService

trait AccountTests { this: TestService =>
  def testAccount() = runTest { () =>
    val testUser = "test-account"
    var account = new Account(UUID.randomUUID, "Invalid user", 0, None, new LocalDateTime())
    val results = Seq(
      {
        account = AccountService.createAccount(testUser)
        TestResult("Account [" + testUser + "] created.")
      },
      {
        AccountService.updateAccountName(account.id, "test-account-new-name")
        TestResult("Account [" + testUser + "] updated with new name.")
      },
      {
        AccountService.removeAccount(account.id)
        TestResult("Account [" + account.id + "] removed.")
      }
    )
    TestResult("account", results)
  }
}
