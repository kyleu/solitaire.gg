package models.rules.impl

import models.rules._

object Bakers extends GameRules(
  id = "bakers",
  completed = true,
  title = "Baker's",
  related = Seq("bakerstwodeck"),
  links = Seq(
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Baker's_Game"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bakers_game.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bakers_game.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Bakers_Game.html.en"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/bakers_game.htm")
  ),
  layout = "fc|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
