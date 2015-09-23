package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): canister
 *   Number of decks (ndecks): 1 (1 deck)
 */
object BritishCanister extends GameRules(
  id = "britishcanister",
  completed = true,
  title = "British Canister",
  like = Some("canister"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/british_canister.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/canister.htm")
  ),
  description = "A difficult version of ^canister^ dating back to the 1890's. It resembles ^americancanister^ but does not allow stack moves and on" +
    "ly kings can fill spaces.",
  layout = "::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
