package services.test

import java.util.UUID

import models.rules.GameRulesSet
import models.test.{Test, Tree}

class RulesTests {
  val all = Tree(Test("rules"), GameRulesSet.all.map(x => testGameRules(x.id).toTree))

  def testGameRules(id: String) = Test(s"rules-$id", () => {
    val rules = GameRulesSet.allByIdWithAliases(id)

    val game = rules.newGame(UUID.randomUUID(), 0, id)
    s"OK (${game.deck.cards.size} cards)"
  })
}
