package models.rules.impl

import models.card.Rank
import models.rules._

object Deuces extends GameRules(
  id = "deuces",
  completed = true,
  title = "Deuces",
  like = Some("busyaces"),
  related = Seq("jacksinthebox", "threescompany", "castoutnines"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Deuces_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/deuces.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/deuces.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/deuces.htm")
  ),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Two),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
