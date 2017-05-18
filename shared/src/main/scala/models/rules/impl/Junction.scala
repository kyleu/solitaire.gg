package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of decks (ndecks): 4 (4 decks)
 *   Ranks in use (ranks): 8129
 */
object Junction extends GameRules(
  id = "junction",
  completed = true,
  title = "Junction",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/junction.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/junction.htm")
  ),
  layout = ":::::::sw|f|.::::t",
  deckOptions = DeckOptions(
    numDecks = 4,
    ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
