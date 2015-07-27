// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 3 (When all stackable cards are off)
 *   Foundation add complete sequences only (F0cs): false
 *   Stock name (S0Nm): Reserve
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUU UUUUUUU UUUUUUU DDDUUUU DDDDUUU DDDDDUU DDDDDDU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): scorpion
 *   Related games (related): dragon
 */
object Chinese extends GameRules(
  id = "chinese",
  completed = true,
  title = "Chinese",
  like = Some("scorpion"),
  related = Seq("dragon"),
  links = Seq(Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/chinese_solitaire.html")),
  description = "This ^scorpion^ variation has a different layout, a smaller stock, and allows cards to be moved to the foundation one at a time.",
  layout = Some("s.f|t"),
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "DDDUUUU",
        "DDDDUUU",
        "DDDDDUU",
        "DDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
