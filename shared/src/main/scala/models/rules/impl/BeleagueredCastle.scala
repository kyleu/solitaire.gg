package models.rules.impl

import models.rules._

object BeleagueredCastle extends GameRules(
  id = "beleagueredcastle",
  completed = true,
  title = "Beleaguered Castle",
  related = Seq("fortress", "citadel", "castlemount", "castleofindolence", "streetsandalleys", "selectivecastle"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Beleaguered_Castle"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/beleaguered_castle.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/beleaguered_castle.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Beleaguered_Castle.html.en")
  ),
  layout = "::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
