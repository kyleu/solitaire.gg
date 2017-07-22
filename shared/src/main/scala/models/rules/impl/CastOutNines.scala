package models.rules.impl

import models.card.Rank
import models.rules._

object CastOutNines extends GameRules(
  id = "castoutnines",
  completed = false,
  title = "Cast Out Nines",
  like = Some("deuces"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cast_out_nines.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Nine),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
