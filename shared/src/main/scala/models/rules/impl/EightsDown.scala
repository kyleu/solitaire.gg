package models.rules.impl

import models.card.Rank
import models.rules._

object EightsDown extends GameRules(
  id = "eightsdown",
  completed = false,
  title = "Eights Down",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eights_down.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Eight),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 8, rankMatchRule = RankMatchRule.Down, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 6,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    rankMatchRuleForBuilding = RankMatchRule.Up,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
    rankMatchRuleForMovingStacks = RankMatchRule.Up
  ))
)
