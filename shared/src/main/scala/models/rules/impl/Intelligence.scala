package models.rules.impl

import models.rules._

object Intelligence extends GameRules(
  id = "intelligence",
  completed = true,
  title = "Intelligence",
  like = Some("labellelucie"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Intelligence_(solitaire)"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Intelligence.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/intelligence.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/intelligence.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/intelligence.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/intelligence.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Intelligence.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/intelligence.html")
  ),
  layout = "s::::f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveToEmptyFoundationAndReplace
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
