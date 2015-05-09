// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Foundation rank match rule (F0r): 32 (Build down)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 128 (Build up)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Low card (lowpip): 8 (8)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object EightsDown extends GameRules(
  id = "eightsdown",
  title = "Eights Down",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eights_down.htm")),
  description = "A ^busyaces^ variant invented by Thomas Warfield, where the foundations build down from eight.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Eight
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
      initialCards = 8,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.Up,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
