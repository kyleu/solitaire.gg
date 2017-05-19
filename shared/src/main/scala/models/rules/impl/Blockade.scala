package models.rules.impl

import models.rules._

object Blockade extends GameRules(
  id = "blockade",
  completed = true,
  title = "Blockade",
  related = Seq("napoleonssquare"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/blockade.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/blockade.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/blockade.php"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Blockade_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/blockade.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/blockade.htm")
  ),
  layout = ":s:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
