package models.rules.impl

import models.rules._

object BakersDozen extends GameRules(
  id = "bakersdozen",
  completed = false,
  title = "Baker's Dozen",
  related = Seq("spanish", "goodmeasure"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dozen.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bakers-dozen.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Baker's_Dozen_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bakers_dozen.html"),
    Link("About.com", "boardgames.about.com/od/solitaire/a/bakers_dozen.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/BakersDozen.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Bakers_Dozen.html.en")
  ),
  layout = ":.f|t|.t",
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
