// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 8
 *   Tableau sets (Tn): 1 (1 tableau set)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): pyramid
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 *   Related games (related): pyramiddozen
 */
object Giza extends GameRules(
  id = "giza",
  title = "Giza",
  like = Some("pyramid"),
  related = Seq("pyramiddozen"),
  links = Seq(
    Link("Michael Keller's Discussion at Solitaire Laboratory", "www.solitairelaboratory.com/giza.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/giza.htm")
  ),
  description = "Michael Keller's variation of ^pyramid^ has a tableau of cards instead of a stock, making it a completely open game.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
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
