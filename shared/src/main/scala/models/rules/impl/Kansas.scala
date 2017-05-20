package models.rules.impl

import models.card.Rank
import models.rules._

object Kansas extends GameRules(
  id = "kansas",
  completed = true,
  title = "Kansas",
  like = Some("rainbow"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Kansas.html.en")),
  layout = "swf|r.::t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
