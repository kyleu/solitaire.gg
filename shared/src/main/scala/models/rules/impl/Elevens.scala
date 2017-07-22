package models.rules.impl

import models.rules._

object Elevens extends GameRules(
  id = "elevens",
  completed = false,
  title = "Elevens",
  like = Some("tens"),
  related = Seq("suitelevens"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Elevens"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Elevens.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/elevens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Elevens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/elevens.htm"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  layout = "sf|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJQK,
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
