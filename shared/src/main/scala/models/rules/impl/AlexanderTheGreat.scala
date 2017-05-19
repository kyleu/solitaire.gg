package models.rules.impl

import models.card.Color
import models.rules._

object AlexanderTheGreat extends GameRules(
  id = "alexanderthegreat",
  completed = true,
  title = "Alexander the Great",
  related = Seq("cloverleaf"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alexander_the_great.htm")),
  layout = "f::f|2t",
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Color.Red)),
      initialCards = 2,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Color.Black)),
      initialCards = 2,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRankOrLowRank
    )
  )
)
