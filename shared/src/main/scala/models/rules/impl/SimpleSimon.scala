package models.rules.impl

import models.rules._

object SimpleSimon extends GameRules(
  id = "simplesimon",
  completed = false,
  title = "Simple Simon",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/simple_simon.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Simple_Simon_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/simple_simon.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/simple-simon.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SimpleSimon.htm"),
    Link("Solitaire Whizz", "www.solitairewhizz.com/how-to-play/simple-simon.shtml"),
    Link("kPatience", "docs.kde.org/stable/en/kdegames/kpat/rules-specific.html#simple-simon")
  ),
  layout = "f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
