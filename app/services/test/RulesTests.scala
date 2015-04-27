package services.test

import java.util.UUID

import models.game.{ GameState, Deck }
import models.game.generated.GameRulesSet
import models.game.variants.GameVariant
import models.game.variants.GameVariant.Description
import models.test.{ Test, Tree }

class RulesTests {
  val all = Tree(Test("rules"), GameRulesSet.all.map(x => testGameRules(x.id).toTree))

  def testGameRules(id: String) = Test("rules-" + id, () => {
    val rules = GameRulesSet.allById(id)
    val description = new Description {
      override val key: String = rules.id
      override val name = rules.title
      override val body = rules.description
    }

    def initialMoves(gameState: GameState, deck: Deck) = {

    }

    var ret = "OK"
    val variant = GameVariant(id, description, UUID.randomUUID(), 0, initialMoves)
    ret = ret + " (" + variant.deck.cards.size + " cards)"
    ret
  })
}
