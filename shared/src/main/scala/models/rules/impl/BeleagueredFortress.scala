package models.rules.impl

import models.rules._

object BeleagueredFortress extends GameRules(
  id = "beleagueredfortress",
  completed = false,
  title = "Beleaguered Fortress",
  like = Some("fortress"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/beleaguered-castle-2.htm")),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
