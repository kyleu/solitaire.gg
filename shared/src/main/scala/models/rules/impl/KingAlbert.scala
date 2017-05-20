package models.rules.impl

import models.rules._

object KingAlbert extends GameRules(
  id = "kingalbert",
  completed = true,
  title = "King Albert",
  related = Seq("queenvictoria", "muse", "raglan"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/King_Albert_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/king_albert.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/king_albert.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/king_albert.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/king_albert.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/King_Albert.html.en")
  ),
  layout = "w:f|t",
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
      numPiles = 9,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
