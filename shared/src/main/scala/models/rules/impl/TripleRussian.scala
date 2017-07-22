package models.rules.impl

import models.rules._

object TripleRussian extends GameRules(
  id = "triplerussian",
  completed = true,
  title = "Triple Russian",
  like = Some("russian"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_russian.htm")),
  layout = ".f|t",
  deckOptions = DeckOptions(numDecks = 3),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DUUUUUU",
        "DDUUUUUU",
        "DDDUUUUUU",
        "DDDDUUUUUU",
        "DDDDDUUUUUU",
        "DDDDDDUUUUUU",
        "DDDDDDDUUUUUU",
        "DDDDDDDDUUUUUU",
        "DDDDDDDDDUUUUUU",
        "DDDDDDDDDDDU",
        "DDDDDDDDDDDDDU",
        "DDDDDDDDDDDDDDU",
        "DDDDDDDDDDDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
