package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 2 (2)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Reserve initial cards (R0d): 6
 *   Number of reserve piles (R0n): 3
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Left mouse interface function (leftfunc): 2
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): evenandodd
 *   Touch interface function (touchfunc): 2
 */
object Boulevard extends GameRules(
  id = "boulevard",
  completed = true,
  title = "Boulevard",
  related = Seq("evenandodd"),
  links = Seq(Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/boulevard.htm")),
  description = "No building on the tableau, three reserve piles, and foundations that build up by twos.",
  layout = "sw.ff|rt",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 3,
      initialCards = 6,
      cardsFaceDown = -1
    )
  )
)
