// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Main Foundation
 *   Maximum cards for foundation (F0m): 12
 *   Foundation name (F1Nm): Kings Foundation
 *   Auto-move cards to foundation (F1a): 1 (Whenever possible)
 *   Foundation low rank (F1b): 13
 *   Number of foundation piles (F1n): 1 (1 stack)
 *   Foundation rank match rule (F1r): 64 (Build equal)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Similar to (like): takingsilk
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Floradora extends GameRules(
  id = "floradora",
  title = "Floradora",
  like = Some("takingsilk"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/floradora.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/floradora.htm")
  ),
  description = "A two-deck variation of ^thirtysix^ with an extra foundation pile for kings, but no stack moves.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Main Foundation",
      numPiles = 8,
      maxCards = 12,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
