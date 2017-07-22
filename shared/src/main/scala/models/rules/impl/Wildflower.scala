package models.rules.impl

import models.rules._

object Wildflower extends GameRules(
  id = "wildflower",
  completed = true,
  title = "Wildflower",
  like = Some("flowergarden"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/wildflower.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/flower-garden.htm")
  ),
  layout = ":w|:f|t",
  waste = Some(WasteRules(name = "Bouquet", cardsShown = 20)),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    name = "Flower Beds",
    numPiles = 6,
    initialCards = InitialCards.Count(6),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
  ))
)
