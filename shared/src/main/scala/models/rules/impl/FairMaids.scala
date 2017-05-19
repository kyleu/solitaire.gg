package models.rules.impl

import models.rules._

object FairMaids extends GameRules(
  id = "fairmaids",
  completed = true,
  title = "Fair Maids",
  like = Some("willothewisp"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fair_maids.htm")),
  layout = "s.f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(StockRules(dealTo = StockDealTo.TableauNonEmpty, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, suitMatchRule = SuitMatchRule.AlternatingColors, moveCompleteSequencesOnly = true)),
  tableaus = Seq(TableauRules(initialCards = InitialCards.Count(4), suitMatchRuleForBuilding = SuitMatchRule.Any))
)
