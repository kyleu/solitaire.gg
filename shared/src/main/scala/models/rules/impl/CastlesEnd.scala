package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Reserve initial cards (R0d): 1
 *   Number of reserve piles (R0n): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 10
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Similar to (like): chessboard
 *   Low card (lowpip): -2 (?)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CastlesEnd extends GameRules(
  id = "castlesend",
  completed = true,
  title = "Castle's End",
  like = Some("chessboard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castles_end.htm")),
  layout = ":f::r|t",
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
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 2,
      initialCards = 1,
      cardsFaceDown = -1
    )
  )
)
