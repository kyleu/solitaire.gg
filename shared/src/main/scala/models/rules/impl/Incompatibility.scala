package models.rules.impl

import models.rules._

object Incompatibility extends GameRules(
  id = "incompatibility",
  completed = false,
  title = "Incompatibility",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/incompatibility.htm")),
  layout = "sf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauNonEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
