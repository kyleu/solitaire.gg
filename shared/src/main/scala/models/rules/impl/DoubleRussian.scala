// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUU DUUUUUU DDUUUUUU DDDUUUUUU DDDDUUUUUU DDDDDUUUUUU DDDDDDUUUUUU DDDDDDDUUUU...
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): russian
 *   Number of decks (ndecks): 2 (2 decks)
 *   *unused (unused): temp_hack
 */
object DoubleRussian extends GameRules(
  id = "doublerussian",
  completed = false,
  title = "Double Russian",
  like = Some("russian"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_russian.htm")),
  description = "A two-deck version of ^russian^ solitaire.",
  layout = "f|t",
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
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUU",
        "DUUUUUU",
        "DDUUUUUU",
        "DDDUUUUUU",
        "DDDDUUUUUU",
        "DDDDDUUUUUU",
        "DDDDDDUUUUUU",
        "DDDDDDDUUUUUU",
        "DDDDDDDDUUUUUU",
        "DDDDDDDDDUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)