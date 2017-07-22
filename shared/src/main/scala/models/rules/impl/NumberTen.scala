package models.rules.impl

import models.rules._

object NumberTen extends GameRules(
  id = "numberten",
  completed = true,
  title = "Number Ten",
  related = Seq("rankandfile"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/number_ten.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/number_ten.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/NumberTen.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/number-ten.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NumberTen.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/number_ten.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/number_ten.htm")
  ),
  layout = "swf|.t",
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(2)
    )
  )
)
