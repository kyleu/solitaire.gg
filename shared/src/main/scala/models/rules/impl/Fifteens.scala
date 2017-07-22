package models.rules.impl

import models.rules._

object Fifteens extends GameRules(
  id = "fifteens",
  completed = true,
  title = "Fifteens",
  like = Some("straightfifteens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteens.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fifteens.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fifteens.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Fifteens.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Fifteens.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fifteens.php"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/fifteens.htm")
  ),
  layout = ":::::s:f|t",
  cardRemovalMethod = CardRemovalMethod.RemoveSetsAddingToFifteenOrFour10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
