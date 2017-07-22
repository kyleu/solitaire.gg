package models.rules.impl

import models.rules._

object RoyalFamily extends GameRules(
  id = "royalfamily",
  completed = true,
  title = "Royal Family",
  like = Some("indefatigable"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_family.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-family.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/royal_family.php"),
    Link("Jan Wolter's Experiments", "/article/indefatigable.html")
  ),
  layout = "::::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 1
    )
  )
)
