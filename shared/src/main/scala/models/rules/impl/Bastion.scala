package models.rules.impl

import models.rules._

object Bastion extends GameRules(
  id = "bastion",
  completed = true,
  title = "Bastion",
  like = Some("fortress"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bastion.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/bastion.htm")
  ),
  layout = ":.f:c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules(numPiles = 2, initialCards = 2))
)
