// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Deal order (RDd): 1|0|8
 *   Allowed pick ups/redeals (RDn): -1 (Unlimited)
 *   Pickup order (RDp): 1|0|8
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Related games (related): indefatigable, unusual, ripplefan, perseverancea
 *   Right mouse interface function (rightfunc): 0x0
 */
object Cruel extends GameRules(
  id = "cruel",
  title = "Cruel",
  related = Seq("indefatigable", "unusual", "ripplefan", "perseverancea"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cruel.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Cruel.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Cruel_(solitaire)"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Cruel.html.en"),
    Link("Dan Fletcher's Strategy Guide.", "ezinearticles.com/?Cruel-Solitaire-Strategy-Guide&id=111462"),
    Link("L. Schaffer's Rules and Strategy Guide", "www.hobbyhub360.com/index.php/how-to-play-cruel-solitaire-14162/"),
    Link("Jan Wolter's Analysis", "/article/cruel.html"),
    Link("An 1898 Description of a game called \"Perseverance\".", "howtoplaysolitaire.blogspot.com/2010/06/perseverance-single-deck-solitaire-game.html")
  ),
  description = "A game where you can redeal the tableau as often as you like, so long as you can take off at least one card between deals.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  complete = false
)

