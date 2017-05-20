package models.rules.impl

import models.rules._

object Pyramid extends GameRules(
  id = "pyramid",
  completed = true,
  title = "Pyramid",
  related = Seq("kingtut", "giza", "doublepyramid", "darkpyramid", "apophis", "cheops"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pyramid.htm"),
    Link("Michael Keller's Discussion", "www.solitairelaboratory.com/pyramid.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Pyramid_(solitaire)"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/pyramid.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Thirteen.html.en"),
    Link("Erik Arneson's Rules at About.com", "boardgames.about.com/od/solitaire/a/pyramid.htm"),
    Link("Dan Fletcher's Strategy Guide at Solitaire Central", "www.solitairecentral.com/articles/PyramidSolitaireAStrategyGuideForBeginners.html")
  ),
  layout = "p|::.swf",
  victoryCondition = VictoryCondition.NoneInPyramid,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  )
)
