package models.rules.impl

import models.rules._

object Acme extends GameRules(
  id = "acme",
  completed = true,
  title = "Acme",
  like = Some("canfield"),
  related = Seq("coyote"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/acme.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/acme.htm"),
    Link("Jan Wolter's Experiments", "/article/acme.html")
  ),
  layout = "swf|r::t",
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
