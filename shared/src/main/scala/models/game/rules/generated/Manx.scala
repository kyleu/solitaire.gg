// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Cells name (C0Nm): Tail
 *   Number of cells (C0n): 1
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Related games (related): tabbycat
 */
object Manx extends GameRules(
  id = "manx",
  title = "Manx",
  related = Seq("tabbycat"),
  links = Seq(
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Manx.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/manx.html")
  ),
  description = "Build complete sequences by rearranging four piles regardless of suit in this game invented by Rick Holzgrafe of Solitaire Til Daw" +
    "n.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      pluralName = "Tail",
      numPiles = 1
    )
  ),
  complete = false
)

