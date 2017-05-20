package models.rules.impl

import models.rules._

object Unlimited extends GameRules(
  id = "unlimited",
  completed = true,
  title = "Unlimited",
  like = Some("interchange"),
  related = Seq("singleinterchange"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/unlimited.htm")),
  layout = "swf|::t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
