package services.test

import java.util.UUID

import models.game.generated.GameRulesSet
import models.game.variants.GameVariant
import models.test.{ Test, Tree }

class RulesTests {
  val all = Tree(Test("rules"), GameRulesSet.all.map(x => testGameRules(x.id).toTree))

  def testGameRules(id: String) = Test("rules-" + id, () => {
    val rules = GameRulesSet.allById(id)

    var ret = "OK"
    val variant = GameVariant(id, UUID.randomUUID(), 0)
    ret = ret + " (" + variant.gameState.deck.cards.size + " cards)"
    ret
  })
}
