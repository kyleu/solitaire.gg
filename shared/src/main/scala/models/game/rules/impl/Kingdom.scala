// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Kingdom extends GameRules(
  id = "kingdom",
  title = "Kingdom",
  links = Seq(
    Link("Solsuite Solitaire", "www.solsuite.com/games/kingdom.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingdom.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/kingdom.php")
  ),
  description = "A game where no building is allowed in the tableau and suits are ignored while building up the tableau. Our version may be slightl" +
    "y less dreadful than the usual.",
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
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
