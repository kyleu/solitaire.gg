// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Cells name (C0Nm): Tail
 *   Number of cells (C0n): 0
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Tableau name (T1Nm): Tail
 *   Tableau initial cards (T1d): 0 (None)
 *   Tableau piles (T1n): 1
 *   Tableau suit match rule for building (T1s): 0 (May not build)
 *   Tableau rank match rule for moving stacks (T1tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T1ts): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 11
 *   Similar to (like): manx
 *   Related games (related): tabbycat
 */
object Tabbycat extends GameRules(
  id = "tabbycat",
  title = "Tabbycat",
  like = Some("manx"),
  related = Seq("tabbycat"),
  description = "An easier version of ^manx^ which allows a sequence to be parkted in the tail. Also invented by Rick Holzgrafe of Solitaire Til Da" +
  "wn.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauFirstSet,
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
      emptyFilledWith = TableauFillEmptyWith.Aces
    ),
    TableauRules(
      name = "Tail",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

