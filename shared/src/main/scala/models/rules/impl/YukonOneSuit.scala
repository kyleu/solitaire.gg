package models.rules.impl

import models.card.Suit
import models.rules._

object YukonOneSuit extends GameRules(
  id = "yukononesuit",
  completed = true,
  title = "Yukon One Suit",
  layout = ":.f|t",
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon_one_suit.htm")),
  deckOptions = DeckOptions(numDecks = 4, suits = Seq(Suit.Spades)),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "U",
      "DUUUUU",
      "DDUUUUU",
      "DDDUUUUU",
      "DDDDUUUUU",
      "DDDDDUUUUU",
      "DDDDDDUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
