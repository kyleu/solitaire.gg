package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Tableau
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   Number of cards shown (W0cardsShown): 20
 *   *W0s (W0s): true
 *   Similar to (like): flowergarden
 */
object Brigade extends GameRules(
  id = "brigade",
  completed = false,
  title = "Brigade",
  like = Some("flowergarden"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/brigade.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/brigade.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/brigade.html")
  ),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve",
      cardsShown = 20
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
