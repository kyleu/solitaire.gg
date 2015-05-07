// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DUUUUUU DDUUUUUU DDDUUUUUU DDDDUUUUUU DDDDDUUUUUU DDDDDDUUUUUU DDDDDDDUUUUUU D...
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): doubleyukon
 *   Number of decks (ndecks): 4 (4 decks)
 *   Related games (related): quadrupleyukon
 */
object QuadrupleYukon extends GameRules(
  id = "quadrupleyukon",
  title = "Quadruple Yukon",
  like = Some("doubleyukon"),
  related = Seq("quadrupleyukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadruple_yukon.htm")),
  description = "A four-deck variation of ^yukon^",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUUU",
        "DDUUUUUU",
        "DDDUUUUUU",
        "DDDDUUUUUU",
        "DDDDDUUUUUU",
        "DDDDDDUUUUUU",
        "DDDDDDDUUUUUU",
        "DDDDDDDDUUUUUU",
        "DDDDDDDDDUUUUUU",
        "DDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDDUUUUUU",
        "DDDDDDDDDDDDDUUUUU",
        "DDDDDDDDDDDDDDUUUUU",
        "DDDDDDDDDDDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  complete = false
)

