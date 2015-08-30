// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 */
object Queenie extends GameRules(
  id = "queenie",
  completed = true,
  title = "Queenie",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/queenie.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Queenie.html"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/queenie.html")
  ),
  description = "Build stacks of cards in alternating colors as in ^klondike^, move arbitrary groups of cards as in ^yukon^, and deal waves of card" +
    "s onto to the tableau, as in ^spider^.",
  layout = "s:f|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)