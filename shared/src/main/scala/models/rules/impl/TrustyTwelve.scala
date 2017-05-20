package models.rules.impl

import models.rules._

object TrustyTwelve extends GameRules(
  id = "trustytwelve",
  completed = true,
  title = "Trusty Twelve",
  related = Seq("bunker", "knottynines", "sweetsixteen", "upandup"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/trusty_twelve.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/trusty_twelve.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/trusty-twelve.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/TrustyTwelve.htm")
  ),
  layout = "s|2t",
  victoryCondition = VictoryCondition.NoneInStock,
  stock = Some(
    StockRules(
      cardsShown = 19,
      dealTo = StockDealTo.TableauEmpty,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      cardsShown = 2,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
