// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Skippy extends GameRules(
  id = "skippy",
  title = "Skippy",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/skippy.htm")),
  description = "A game invented by Lillian Davies and Christa Baran.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules())
)
