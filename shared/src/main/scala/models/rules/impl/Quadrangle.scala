package models.rules.impl

import models.card.Rank
import models.rules._

object Quadrangle extends GameRules(
  id = "quadrangle",
  completed = true,
  title = "Quadrangle",
  like = Some("corona"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadrangle.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/quadrangle.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/quadrangle.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/quadrangle.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/quadrangle.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Quadrangle.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
