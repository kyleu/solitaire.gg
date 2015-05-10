// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Similar to (like): singlerail
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DoubleRail extends GameRules(
  id = "doublerail",
  title = "Double Rail",
  like = Some("singlerail"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_rail.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_rail.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/double_rail.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-rail.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/DoubleRail.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/double_rail.htm")
  ),
  description = "A ^fortythieves^ variation where we build regardless of suit and can move stacks.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
