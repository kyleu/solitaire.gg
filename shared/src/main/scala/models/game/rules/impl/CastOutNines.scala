// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 0 (None)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): deuces
 *   Low card (lowpip): 9 (9)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CastOutNines extends GameRules(
  id = "castoutnines",
  title = "Cast Out Nines",
  like = Some("deuces"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cast_out_nines.htm")),
  description = "A difficult variation of ^deuces^ or ^busyaces^ where no cards are already on the foundation and there are only seven tableau pile" +
    "s. Invented by Thomas Warfield.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Nine
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
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
