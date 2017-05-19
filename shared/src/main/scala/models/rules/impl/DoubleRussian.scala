package models.rules.impl

import models.rules._

object DoubleRussian extends GameRules(
  id = "doublerussian",
  completed = true,
  title = "Double Russian",
  like = Some("russian"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_russian.htm")),
  layout = ":f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 10,
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "UUUUU",
      "DUUUUUU",
      "DDUUUUUU",
      "DDDUUUUUU",
      "DDDDUUUUUU",
      "DDDDDUUUUUU",
      "DDDDDDUUUUUU",
      "DDDDDDDUUUUUU",
      "DDDDDDDDUUUUUU",
      "DDDDDDDDDUUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
