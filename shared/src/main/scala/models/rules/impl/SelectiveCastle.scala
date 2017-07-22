package models.rules.impl

import models.card.Rank
import models.rules._

object SelectiveCastle extends GameRules(
  id = "selectivecastle",
  completed = true,
  title = "Selective Castle",
  like = Some("beleagueredcastle"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/selective_castle.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Beleaguered_Castle")
  ),
  layout = "::f|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
