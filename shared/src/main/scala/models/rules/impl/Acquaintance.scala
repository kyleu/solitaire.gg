package models.rules.impl

import models.rules._

object Acquaintance extends GameRules(
  id = "acquaintance",
  completed = true,
  title = "Acquaintance",
  like = Some("auldlangsyne"),
  related = Seq("quadrennial"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/acquaintance.htm"),
    Link("Michael Smoker on HobbyHub", "www.hobbyhub360.com/index.php/acquaintance-solitaire-game-10370/")
  ),
  layout = "sf|.t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, suitMatchRule = SuitMatchRule.Any)),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(SpecialRules(redealsAllowed = 2))
)
