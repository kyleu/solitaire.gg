// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Left mouse interface function (leftfunc): 1
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Card removal method (pairs): 10 (Remove kings or pairs adding to 13)
 *   Related games (related): kingtut, giza, doublepyramid, darkpyramid, apophis, cheops
 */
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
  description = "A classic pair-removal game with a triangular tableau.",
  layout = Some("p|::.swf"),
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
