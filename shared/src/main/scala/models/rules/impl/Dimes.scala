package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): busyaces
 *   Low card (lowpip): 10 (X)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Dimes extends GameRules(
  id = "dimes",
  completed = false,
  title = "Dimes",
  like = Some("busyaces"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dimes.htm")),
  description = "A variation on ^deuces^ with fewer tableau piles.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Ten
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
  )
)
