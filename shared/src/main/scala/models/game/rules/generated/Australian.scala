// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 0x1fff
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 */
object Australian extends GameRules(
  id = "australian",
  title = "Australian",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Australian_Patience_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/australian_patience.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/australian_patience.html"),
    Link("Games for One", "mac.gamesforone.com/rules/au_patience.html"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Australian%20Patience.shtml")
  ),
  description = "A variation of ^klondike^ that allows non-top cards to be moved (with the cards on top of them) as in ^yukon^.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  ),
  complete = false
)

