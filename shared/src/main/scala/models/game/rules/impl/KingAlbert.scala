// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Related games (related): queenvictoria, muse, raglan
 *   Enable super moves, whatever those are (supermoves): 1
 */
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
  description = "This game, one of several games also known as \"Idiot's Delight,\" has a triangular tableau and seven reserve cards, all playable." +
    " It's usually unsolvable.",
  layout = Some("w:f|t"),
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
