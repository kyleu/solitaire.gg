package models.rules.impl

import models.rules._

object EightOff extends GameRules(
  id = "eightoff",
  completed = true,
  title = "Eight Off",
  related = Seq("eighton"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eight_off.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/EightOff.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/eight_off.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Eight_Off.html.en"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/eight_off.htm")
  ),
  layout = "::f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(numPiles = 8, initialCards = 4))
)
