// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Pyramid name (P0Nm): Backbone
 *   *P0ds (P0ds): ==========-
 *   *P0n (P0n): 2
 *   Number of pyramids (Pn): 1 (1 pyramid)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Backbone extends GameRules(
  id = "backbone",
  title = "Backbone",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/backbone.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Backbone_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/backbone.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/backbone.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Backbone.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-backbone-solitaire-14353/")
  ),
  description = "A difficult game of Victorian origin with a forked reserve pile",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
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
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation")
    )
  )
)
