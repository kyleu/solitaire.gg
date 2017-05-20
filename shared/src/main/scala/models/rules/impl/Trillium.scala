package models.rules.impl

import models.rules._

object Trillium extends GameRules(
  id = "trillium",
  completed = false,
  title = "Trillium",
  like = Some("spider"),
  related = Seq("lily"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trillium.htm")),
  layout = "s::f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  )
)
