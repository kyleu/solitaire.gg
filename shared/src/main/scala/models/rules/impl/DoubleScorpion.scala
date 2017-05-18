package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Keep foundation off-screen (F0i): true
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDUUUUUUU DDDDUUUUUUU DDDDUUUUUUU DDDDUUUUUUU DDDDUUUUUU UUUUUUUUUU UUUUUUUUUU...
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DoubleScorpion extends GameRules(
  id = "doublescorpion",
  completed = true,
  title = "Double Scorpion",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_scorpion.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-scorpion.htm")
  ),
  layout = "f|t",
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
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDUUUUUUU",
        "DDDDUUUUUUU",
        "DDDDUUUUUUU",
        "DDDDUUUUUUU",
        "DDDDUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
