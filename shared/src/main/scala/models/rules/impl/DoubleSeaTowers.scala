// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 5 (5 cards)
 *   Number of cells (C0n): 9
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 9 (9 cards)
 *   Tableau piles (T0n): 11
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object DoubleSeaTowers extends GameRules(
  id = "doubleseatowers",
  completed = false,
  title = "Double Sea Towers",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_seatowers.htm")),
  description = "A two-deck version of the ^freecell^ variation known as ^seatowers^.",
  layout = Some("f|c|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 11,
      initialCards = InitialCards.Count(9),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 9,
      initialCards = 5
    )
  )
)