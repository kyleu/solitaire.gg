// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): 1 (Yes)
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 1
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Tableau initial cards (T1d): 6 (6 cards)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 16
 *   Tableau suit match rule for building (T1s): 0 (May not build)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): fourteenout
 *   Number of decks (ndecks): 2 (2 decks)
 *   Card removal method (pairs): 11 (Remove pairs adding to 14)
 */
object Juvenile extends GameRules(
  id = "juvenile",
  title = "Juvenile",
  like = Some("fourteenout"),
  links = Seq(
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/juvenile.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/juvenile.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/juvenile.php")
  ),
  description = "A two-deck game where you remove pairs that add to fourteen.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 1,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 16,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
