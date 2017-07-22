package models.rules.impl

import models.rules._

object RougeForty extends GameRules(
  id = "rougeforty",
  completed = true,
  title = "Rouge Forty",
  like = Some("rougeetnoir"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rouge_forty.htm")),
  layout = "s:f:f:f|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueColors),
      suitMatchRule = SuitMatchRule.SameColor
    ),
    FoundationRules(
      setNumber = 2,
      numPiles = 4,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
