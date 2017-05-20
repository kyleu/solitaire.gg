package models.rules.impl

import models.rules._

object Steps extends GameRules(
  id = "steps",
  completed = false,
  title = "Steps",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/steps.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/steps.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Steps.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/steps.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Steps.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/steps.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/steps.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
