// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Maximum cards for foundation (F0m): 7
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   Foundation low rank (F1b): 2 (2)
 *   Foundation initial cards (F1d): -1
 *   Maximum cards for foundation (F1m): 6
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Foundation Sets (Fn): 2
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 9
 *   Tableau rank match rule for building (T0r): 16 (Build down by 2)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 16 (Build down by 2)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object MountOlympus extends GameRules(
  id = "mountolympus",
  completed = true,
  title = "Mount Olympus",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Mount_Olympus_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/mount_olympus.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/mount_olympus.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#mount")
  ),
  description = "Build by twos, so odds and evens are in separate sequences on the tableau and separate piles on the foundation.",
  layout = Some("sf|:f|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 7,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCards = 8,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.DownBy2,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.DownBy2,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
