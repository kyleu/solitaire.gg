package models.rules.impl

import models.card.Rank
import models.rules._

object Stalactites extends GameRules(
  id = "stalactites",
  completed = true,
  title = "Stalactites",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Stalactites_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stalactites.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/stalactites.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Stalactites.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/stalactites.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/stalactites.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stalactites.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/stalactites.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/stalactites.html")
  ),
  layout = "f::c|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(CellRules(numPiles = 2))
)
