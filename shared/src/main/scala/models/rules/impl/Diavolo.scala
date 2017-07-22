package models.rules.impl

import models.rules._

object Diavolo extends GameRules(
  id = "diavolo",
  completed = false,
  title = "Diavolo",
  related = Seq("rougeetnoir"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/easy_diavolo.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/diavolo.htm"),
    Link("Elton Gahr at HobbyHub", "www.hobbyhub360.com/index.php/view-article/1879401/")
  ),
  layout = "sw|f.f.f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
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
  tableaus = IndexedSeq(TableauRules(numPiles = 9, emptyFilledWith = FillEmptyWith.HighRank))
)
