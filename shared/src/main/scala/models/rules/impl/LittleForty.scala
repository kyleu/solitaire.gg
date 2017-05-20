package models.rules.impl

import models.rules._

object LittleForty extends GameRules(
  id = "littleforty",
  completed = true,
  title = "Little Forty",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_forty.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/little_forty.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleForty.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/LittleForty.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/little_forty.htm")
  ),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
