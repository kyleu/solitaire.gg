package models.rules.impl

import models.rules._

object KingsdownEights extends GameRules(
  id = "kingsdowneights",
  completed = false,
  title = "Kingsdown Eights",
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/Kingsdown-Eights.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingsdown_eights.htm")
  ),
  layout = "sf|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Reserve, maximumDeals = Some(1), cardsDealt = StockCardsDealt.Count(4))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 8, initialCards = 4))
)
