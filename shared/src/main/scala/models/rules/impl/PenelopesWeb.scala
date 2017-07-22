package models.rules.impl

import models.rules._

object PenelopesWeb extends GameRules(
  id = "penelopesweb",
  completed = false,
  title = "Penelope's Web",
  like = Some("streetsandalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/penelopes_web.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/penelope-web.htm")
  ),
  layout = "f|t",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
