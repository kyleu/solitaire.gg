// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Keep foundation off-screen (F0i): true
 *   Foundation Sets (Fn): 1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau sets (Tn): 0 (0 tableau sets)
 *   Playable waste cards (W0a): true
 *   Number of cards shown (W0cardsShown): 52
 *   Left mouse interface function (leftfunc): 4
 *   Card removal method (pairs): 20 (Stack cards of same rank/suit in waste)
 *   Touch interface function (touchfunc): 4
 */
object Accordion extends GameRules(
  id = "accordion",
  title = "Accordion",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Accordion_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/accordion.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/accordion.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/accordion.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Accordion.html.en"),
    Link("Michael Keller's Excellent Accordion Guide", "www.solitairelaboratory.com/accordion.html")
  ),
  description = "This deck compression game was once known as \"Idle Year\" because it was believed that you could play for a year without winning," +
    " but players have now discovered strategies that make it possible to win almost every game.",
  layout = Some("wf"),
  cardRemovalMethod = CardRemovalMethod.StackSameRankOrSuitInWaste,
  waste = Some(
    WasteRules(
      cardsShown = 52
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  )
)
