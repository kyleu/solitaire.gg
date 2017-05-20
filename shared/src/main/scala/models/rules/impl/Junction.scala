package models.rules.impl

import models.card.Rank
import models.rules._

object Junction extends GameRules(
  id = "junction",
  completed = true,
  title = "Junction",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/junction.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/junction.htm")
  ),
  layout = ":::::::sw|f|.::::t",
  deckOptions = DeckOptions(numDecks = 4, ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
