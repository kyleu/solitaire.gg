package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   *P0ds (P0ds): ++++++++
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Number of waste piles (W0n): 3
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): doublepyramid
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 */
object Anubis extends GameRules(
  id = "anubis",
  completed = false,
  title = "Anubis",
  like = Some("doublepyramid"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/anubis.htm")),
  description = "A variation of ^doublepyramid^ with three waste piles.",
  layout = "swf|p",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(
    WasteRules(
      numPiles = 3
    )
  ),
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
