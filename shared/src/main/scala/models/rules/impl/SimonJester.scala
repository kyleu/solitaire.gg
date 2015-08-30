// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Keep foundation off-screen (F0i): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUUUUUUU UUUUUUUUUUUUU UUUUUUUUUUUU UUUUUUUUUUU UUUUUUUUUU UUUUUUUUU UUUUUU...
 *   Tableau piles (T0n): 14
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 */
object SimonJester extends GameRules(
  id = "simonjester",
  completed = false,
  title = "Simon Jester",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simon_jester.htm")),
  description = "A two-deck variant of ^simplesimon^ invented by Adam Selene. It is like ^spider^ except that all cards start face up in a triangul" +
    "ar tableau and there are no further cards be dealt.",
  layout = Some("f|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 14,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUUU",
        "UUUUUUUUUUUU",
        "UUUUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)