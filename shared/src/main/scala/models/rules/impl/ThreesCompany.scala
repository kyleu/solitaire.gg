package models.rules.impl

import models.card.Rank
import models.rules._

object ThreesCompany extends GameRules(
  id = "threescompany",
  completed = false,
  title = "Three's Company",
  like = Some("deuces"),
  related = Seq("foursup"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/threes_company.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Three),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
