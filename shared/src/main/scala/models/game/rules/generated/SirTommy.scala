// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): 1 (Stock)
 *   Tableau piles (T0n): 4
 *   May move to non-empty tableau from (T0o): 1 (Stock)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Related games (related): alternate, ladybetty
 */
object SirTommy extends GameRules(
  id = "sirtommy",
  title = "Sir Tommy",
  related = Seq("alternate", "ladybetty"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Sir_Tommy"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sir_tommy.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/sir_tommy.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/sir_tommy.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/sir-tommy.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Sirtommy.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/sir_tommy.php"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/numerica.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Sir_Tommy.html.en")
  ),
  description = "A classic old solitaire game where cards may be placed anywhere on the tableau, but cannot be rearranged.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      mayMoveToNonEmptyFrom = Seq("Stock"),
      mayMoveToEmptyFrom = Seq("Stock")
    )
  )
)
