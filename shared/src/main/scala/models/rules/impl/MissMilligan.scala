package models.rules.impl

import models.rules._

object MissMilligan extends GameRules(
  id = "missmilligan",
  completed = false,
  title = "Miss Milligan",
  related = Seq("imperialguards"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Miss_Milligan"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/miss_milligan.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/MissMilliganEasy.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/miss_milligan.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/miss_milligan.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/MissMilligan.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/miss_milligan.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/miss-milligan.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/miss_milligan.htm")
  ),
  layout = "sf|:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1),
      createPocketWhenEmpty = true
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
