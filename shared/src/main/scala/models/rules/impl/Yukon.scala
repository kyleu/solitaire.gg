package models.rules.impl

import models.rules._

object Yukon extends GameRules(
  id = "yukon",
  completed = true,
  title = "Yukon",
  related = Seq("yukoncells", "brisbane", "yukononesuit", "yukonicplague", "alaska", "yukonkings", "yakutatbay"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Yukon_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/yukon.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/yukon.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Yukon.html.en"),
    Link("About Dot Com", "boardgames.about.com/od/solitaire/a/yukon.htm"),
    Link("Dan Fletcher's Strategy Guide", "www.solitairecentral.com/articles/YukonSolitaireStrategyGuide.html")
  ),
  layout = ":::f|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Custom,
    customInitialCards = Seq(
      "U",
      "DUUUUU",
      "DDUUUUU",
      "DDDUUUUU",
      "DDDDUUUUU",
      "DDDDDUUUUU",
      "DDDDDDUUUUU"
    ),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForMovingStacks = SuitMatchRule.Any,
    rankMatchRuleForMovingStacks = RankMatchRule.Any,
    emptyFilledWith = FillEmptyWith.HighRank
  ))
)
