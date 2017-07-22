package models.rules.impl

import models.rules._

object RankAndFile extends GameRules(
  id = "rankandfile",
  completed = false,
  title = "Rank and File",
  like = Some("numberten"),
  related = Seq("emperor"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rank_and_file.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/RankAndFile.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RankandFile.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/rank_and_file.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/rank_and_file.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4)
    )
  )
)
