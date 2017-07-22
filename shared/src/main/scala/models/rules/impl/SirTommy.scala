package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object SirTommy extends GameRules(
  id = "sirtommy",
  completed = false,
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
  layout = "sf|t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Stock),
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock)
    )
  )
)
