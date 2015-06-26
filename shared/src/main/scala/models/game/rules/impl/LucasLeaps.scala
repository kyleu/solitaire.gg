// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): waningmoon
 *   Number of decks (ndecks): 2 (2 decks)
 *   Custom suits (suits): 0
 *   Enable super moves, whatever those are (supermoves): 0
 */
object LucasLeaps extends GameRules(
  id = "lucasleaps",
  title = "Lucas Leaps",
  like = Some("waningmoon"),
  links = Seq(
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/lucas.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lucas.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lucas.htm")
  ),
  description = "An easy ^fortythieves^ variant similar to ^waningmoon^ except that sequences can be moved.",
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
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
