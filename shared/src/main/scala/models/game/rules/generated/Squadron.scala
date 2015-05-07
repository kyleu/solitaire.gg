// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

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
 *   Related games (related): robie, napoleonsquadrilateral, famousfifty, fortybandits, limited, elba, threepi...
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Squadron extends GameRules(
  id = "squadron",
  title = "Squadron",
  like = Some("fortythieves"),
  related = Seq(
    "robie", "napoleonsquadrilateral", "famousfifty", "fortybandits", "limited", "elba", "threepirates", "squadron",
    "fortythieves3", "sixtythieves", "littlenapoleon", "eightythieves", "mamysusan", "sanjuanhill", "fortythieves4", "thievesrush",
    "josephine"
  ),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/squadron.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/squadron.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/squadron.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Squadron.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/squadron.php")
  ),
  description = "A much easier version of ^fortytheives^ with three cells.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 3,
      initialCards = 3
    )
  ),
  complete = false
)

