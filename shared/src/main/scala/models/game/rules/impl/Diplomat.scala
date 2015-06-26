// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Empty tableau is filled from (T0fo): 251
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Similar to (like): congress
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): rowsoffour
 */
object Diplomat extends GameRules(
  id = "diplomat",
  title = "Diplomat",
  like = Some("congress"),
  related = Seq("rowsoffour"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Diplomat_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/diplomat.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Diplomat.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/diplomat.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Diplomat.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/diplomat.php"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/diplomat.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/diplomat.html")
  ),
  description = "A variation on ^congress^ or ^fortyandeight^.",
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "reserve", "cell", "foundation")
    )
  )
)
