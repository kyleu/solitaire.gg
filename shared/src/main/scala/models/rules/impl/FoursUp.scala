package models.rules.impl

import models.card.Rank
import models.rules._

object FoursUp extends GameRules(
  id = "foursup",
  completed = true,
  title = "Fours Up",
  like = Some("threescompany"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fours_up.htm")),
  layout = "swf|.::t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Four),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
