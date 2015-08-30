// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 3 (3 cards)
 *   Number of cells (C0n): 3
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Squadron extends GameRules(
  id = "squadron",
  completed = false,
  title = "Squadron",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/squadron.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/squadron.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/squadron.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Squadron.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/squadron.php")
  ),
  description = "A much easier version of ^fortytheives^ with three cells.",
  layout = Some("swf|c|t"),
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3,
      initialCards = 3
    )
  )
)