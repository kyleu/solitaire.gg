// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   *P0ds (P0ds): ++++++++
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): pyramid
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 *   Related games (related): anubis
 */
object DoublePyramid extends GameRules(
  id = "doublepyramid",
  completed = true,
  title = "Double Pyramid",
  like = Some("pyramid"),
  related = Seq("anubis"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_pyramid.htm")),
  description = "Thomas Warfield's two-deck version of ^pyramid^.",
  layout = Some("p|:::.swf"),
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      height = 9,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  )
)
