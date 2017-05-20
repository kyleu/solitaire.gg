package models.rules.impl

import models.rules._

object RougeEtNoir extends GameRules(
  id = "rougeetnoir",
  completed = false,
  title = "Rouge et Noir",
  like = Some("diavolo"),
  related = Seq("rougeforty"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Rouge_et_Noir"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rouge_et_noir.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/rouge_et_noir.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RougeEtNoir.html"),
    Link("An 1898 description", "howtoplaysolitaire.blogspot.com/2010/06/rouge-et-noir-double-deck-solitaire.html")
  ),
  layout = "sfff|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
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
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "DDDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U",
        "U"
      ),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
