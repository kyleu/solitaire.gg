package services.test

import java.util.UUID

import models.rules.GameRulesSet
import models.test.{ Test, Tree }

class RulesTests {
  val all = Tree(Test("rules"), GameRulesSet.all.map(x => testGameRules(x.id).toTree))

  def testGameRules(id: String) = Test(s"rules-$id", () => {
    val rules = GameRulesSet.allById(id)

    val game = rules.newGame(UUID.randomUUID(), 0)
    s"OK (${game.deck.cards.size} cards)"
  })
}
