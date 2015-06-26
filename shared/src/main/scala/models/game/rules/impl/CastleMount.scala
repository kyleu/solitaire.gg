// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 12 (12 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): beleagueredcastle
 *   Number of decks (ndecks): 3 (3 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CastleMount extends GameRules(
  id = "castlemount",
  title = "Castle Mount",
  like = Some("beleagueredcastle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castle_mount.htm")),
  description = "A three-deck version of ^beleagueredcastle^ invented by Thomas Warfield. Since kings can only be moved to empty spaces or the foun" +
    "dation, opening up some columns is the key to the game, except there are 12 cards in each column that need to be gotten out of the" +
    " way first.",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      initialCards = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
