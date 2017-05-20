package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Backbone extends GameRules(
  id = "backbone",
  completed = false,
  title = "Backbone",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/backbone.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Backbone_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/backbone.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/backbone.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Backbone.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-backbone-solitaire-14353/")
  ),
  layout = "swf|t|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  pyramids = Seq(
    PyramidRules(
      name = "Backbone",
      height = 12,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)
