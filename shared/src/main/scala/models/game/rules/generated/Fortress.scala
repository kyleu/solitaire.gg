// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 10
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): beleagueredcastle
 *   Related games (related): bastion, chessboard, fortressofmercy, beleagueredfortress
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Fortress extends GameRules(
  id = "fortress",
  title = "Fortress",
  like = Some("beleagueredcastle"),
  related = Seq("bastion", "chessboard", "fortressofmercy", "beleagueredfortress"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Fortress_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fortress.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fortress.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/fortress.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#fortress"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Fortress.html.en"),
    Link("Jan Wolter's Experimental Analysis", "/article/fortress.html"),
    Link("Michael Keller's Strategy Guide", "solitairelaboratory.com/fortress.html")
  ),
  description = "A classic and usually insolvable ancestor of ^beleagueredcastle^ where you can build both up and down in the tableau.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
