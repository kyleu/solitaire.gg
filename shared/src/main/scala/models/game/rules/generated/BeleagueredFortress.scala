// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 0x0080|0x0020
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   Number of waste piles (W0n): 1
 *   *W0s (W0s): true
 *   Similar to (like): fortress
 *   Related games (related): bastion, chessboard, fortressofmercy, beleagueredfortress
 *   Enable super moves, whatever those are (supermoves): 1
 */
object BeleagueredFortress extends GameRules(
  id = "beleagueredfortress",
  title = "Beleaguered Fortress",
  like = Some("fortress"),
  related = Seq("bastion", "chessboard", "fortressofmercy", "beleagueredfortress"),
  description = "A variation of ^fortress^ with a twelve-card reserve from which all cards are playable.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)

