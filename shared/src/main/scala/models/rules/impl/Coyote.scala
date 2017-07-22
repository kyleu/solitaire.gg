package models.rules.impl

import models.rules._

object Coyote extends GameRules(
  id = "coyote",
  completed = false,
  title = "Coyote",
  like = Some("acme"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/coyote.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Straight_Up.html.en")
  ),
  layout = "swf|r|t",
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
