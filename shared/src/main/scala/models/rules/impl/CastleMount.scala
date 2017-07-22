package models.rules.impl

import models.rules._

object CastleMount extends GameRules(
  id = "castlemount",
  completed = false,
  title = "Castle Mount",
  like = Some("beleagueredcastle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castle_mount.htm")),
  layout = "f|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = IndexedSeq(FoundationRules(numPiles = 12, initialCards = 12, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
