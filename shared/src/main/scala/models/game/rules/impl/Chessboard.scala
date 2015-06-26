// Generated rules for Solitaire.gg.
package models.game.rules.impl

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
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Similar to (like): fortress
 *   Low card (lowpip): -2 (?)
 *   Related games (related): lasker, castlesend
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Chessboard extends GameRules(
  id = "chessboard",
  title = "Chessboard",
  like = Some("fortress"),
  related = Seq("lasker", "castlesend"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chessboard.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/chessboard.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/chessboard.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/chessboard.php"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Chessboard.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Chessboard.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/chessboard.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Chessboard.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-chessboard-solitaire-14077/")
  ),
  description = "A more interesting variation of ^fortress^ where you choose the base card.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
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
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
