package models.rules.impl

import models.card.Rank
import models.rules._

object Czarina extends GameRules(
  id = "czarina",
  completed = false,
  title = "Czarina",
  like = Some("fourseasons"),
  related = Seq("corners"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/czarina.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/czarina.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/czarina.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Czarina.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
