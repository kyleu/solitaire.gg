package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Left Foundation
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation low rank (F0b): 5 (5)
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation name (F1Nm): Right Foundation
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 5 (5)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Tableau name (T0Nm): Left Tableau
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 3
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Tableau name (T1Nm): Right Tableau
 *   Tableau initial cards (T1d): 1 (1 card)
 *   Tableau piles (T1n): 3
 *   Tableau rank match rule for building (T1r): 128 (Build up)
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T1w): true
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of decks (ndecks): 2 (2 decks)
 *   *unused (unused): temp_hack
 */
object Penta extends GameRules(
  id = "penta",
  completed = true,
  title = "Penta",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/penta.htm")),
  layout = ":::.sw|f:f|:t:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Left Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "Right Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
