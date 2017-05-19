package models.rules.impl

import models.card.Color
import models.rules._

object CloverLeaf extends GameRules(
  id = "cloverleaf",
  completed = false,
  title = "Clover Leaf",
  like = Some("alexanderthegreat"),
  related = Seq("alternative"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/clover_leaf.htm")),
  layout = "ff|t",
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
      numPiles = 16,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRankOrLowRank
    )
  )
)
