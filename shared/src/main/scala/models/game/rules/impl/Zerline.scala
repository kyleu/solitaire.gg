// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau name (T1Nm): Reserve
 *   Tableau initial cards (T1d): 0 (None)
 *   Maximum cards per tableau (T1m): 4 (4 cards)
 *   Tableau piles (T1n): 1
 *   May move to non-empty tableau from (T1o): 4 (tableau)
 *   Tableau rank match rule for building (T1r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Low card (lowpip): 13 (K)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Zerline extends GameRules(
  id = "zerline",
  completed = true,
  title = "Zerline",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/zerline.htm")),
  description = "A German game where queens are high and you have a four card storage area.",
  layout = Some("swf|.t:t"),
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.King
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
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    ),
    TableauRules(
      name = "Reserve",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("tableau"),
      maxCards = 4
    )
  )
)
