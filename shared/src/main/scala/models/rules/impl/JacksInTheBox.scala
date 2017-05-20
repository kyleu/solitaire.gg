package models.rules.impl

import models.card.Rank
import models.rules._

object JacksInTheBox extends GameRules(
  id = "jacksinthebox",
  completed = false,
  title = "Jacks in the Box",
  like = Some("deuces"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/jacks_in_the_box.htm")),
  layout = "swf|c|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Jack),
  stock = Some(StockRules(maximumDeals = Some(1))),
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
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
