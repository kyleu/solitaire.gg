package models.rules.impl

import models.rules._

object Lily extends GameRules(
  id = "lily",
  completed = true,
  title = "Lily",
  like = Some("trillium"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lily.htm")),
  layout = "s.:f|t",
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
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
