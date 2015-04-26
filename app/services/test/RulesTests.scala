package services.test

import models.game.Deck
import models.game.generated.GameRulesSet
import models.game.rules.GameRules
import models.test.{ Test, Tree }

import scala.util.Random

class RulesTests {
  val all = Tree(Test("rules"), GameRulesSet.all.map(x => testGameRules(x.id).toTree))

  def testGameRules(id: String) = Test("rules-" + id, () => {
    val rules = GameRulesSet.allById(id)
    val ret = "OK"
    val rng = new Random()
    val deck = getDeck(rules, rng)
    "OK"
  })

  def getDeck(rules: GameRules, rng: Random) = {
    Deck.shuffled(rng, rules.deckOptions.numDecks)
  }
}
