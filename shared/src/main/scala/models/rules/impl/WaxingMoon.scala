// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T1ts): 1 (In same suit)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Custom suits (suits): 83
 */
object WaxingMoon extends GameRules(
  id = "waxingmoon",
  title = "Waxing Moon",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/waxing_moon.htm")),
  description = "A very difficult ^fortythieves^ variant by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Clubs, Suit.Horseshoes)
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
