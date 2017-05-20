package models.rules.impl

import models.rules._

object PileUp extends GameRules(
  id = "pileup",
  title = "Pile Up",
  completed = true,
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteen_puzzle.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Pileon.html.en")
  ),
  layout = "|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Equal,
      maxCards = 4
    )
  )
)
