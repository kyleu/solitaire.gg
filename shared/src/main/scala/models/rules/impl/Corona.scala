package models.rules.impl

import models.rules._

object Corona extends GameRules(
  id = "corona",
  completed = true,
  title = "Corona",
  related = Seq("quadrangle"),
  links = Seq(
    Link("Strategy Guide by Dan Fletcher", "www.solitairecentral.com/articles/HowtoPlayCoronaSolitaire.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/corona.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/corona.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corona.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/corona.php")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
