// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation wraps from king to ace (F0w): true
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   *S0cardsShown (S0cardsShown): 16
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 7
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 6 (To all foundation piles)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): golf
 *   Touch interface function (touchfunc): 2
 *   Victory condition (victory): 5 (All cards on foundation or stock)
 */
object Escalator extends GameRules(
  id = "escalator",
  title = "Escalator",
  like = Some("golf"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pyramid_golf.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Escalator.html.en")
  ),
  description = "Deconstruct a pyramid by building up or down on a single foundation pile. This game is also known by the more descriptive name \"Pyramid Golf.\"",
  victoryCondition = VictoryCondition.NoneInPyramid,
  stock = Some(
    StockRules(
      cardsShown = 16,
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  )
)
