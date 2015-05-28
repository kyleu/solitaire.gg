package services.test

import models.game.rules.PokerRules
import models.game.{ Suit, Rank, Card }
import models.test.{ Test, Tree }

class PokerTests {
  private[this] def cardFrom(s: String) = Card(r = Rank.allByChar(s.toCharArray()(0)), s = Suit.fromChar(s.toCharArray()(1)))
  private[this] def handFrom(s: String) = s.split(",").map(cardFrom).toSeq
  private[this] def testHand(name: String, hand: String) = {
    Test(name, () => { PokerRules.checkHand(handFrom(hand)).toString }).toTree
  }

  val all = Tree(Test("poker-hands"), Seq(
    testHand("royal-flush", "AH,KH,QH,JH,XH"),
    testHand("straight-flush", "QH,JH,XH,9H,8H"),
    testHand("four-of-a-kind", "AH,AS,AD,AC,XH"),
    testHand("full-house", "AH,AS,AD,XH,XC"),
    testHand("flush", "AH,XH,7H,4H,2H"),
    testHand("straight", "AH,KS,QD,JC,XH"),
    testHand("three-of-a-kind", "AH,AS,AD,JC,XH"),
    testHand("two-pair", "AH,AS,3D,3C,XH"),
    testHand("pair", "AH,AS,2D,8C,XH"),
    testHand("high-card", "AH,JH,9S,7D,5C")
  ))
}
