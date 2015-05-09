// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 2
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 10
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): fortress
 *   Related games (related): bastion, chessboard, fortressofmercy, beleagueredfortress
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Bastion extends GameRules(
  id = "bastion",
  title = "Bastion",
  like = Some("fortress"),
  related = Seq("bastion", "chessboard", "fortressofmercy", "beleagueredfortress"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bastion.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/bastion.htm")
  ),
  description = "^fortress^ with cells.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 2,
      initialCards = 2
    )
  )
)
