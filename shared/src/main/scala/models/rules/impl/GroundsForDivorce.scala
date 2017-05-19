package models.rules.impl

import models.rules._

object GroundsForDivorce extends GameRules(
  id = "groundsfordivorce",
  completed = false,
  title = "Grounds for Divorce",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/grounds_for_divorce.htm")),
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
      moveCompleteSequencesOnly = true
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
