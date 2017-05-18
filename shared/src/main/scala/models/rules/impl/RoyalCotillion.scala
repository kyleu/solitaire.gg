package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 2 (2)
 *   Foundation initial cards (F1d): -1
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Reserve initial cards (R0d): 3
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 4
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 16
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Similar to (like): oddandeven
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object RoyalCotillion extends GameRules(
  id = "royalcotillion",
  completed = false,
  title = "Royal Cotillion",
  like = Some("oddandeven"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Royal_Cotillion"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_cotillion.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-cotillion.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/royal_cotillion.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RoyalCotillion.htm")
  ),
  layout = "swff|r|t",
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
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
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
      numPiles = 4,
      initialCards = 3,
      cardsFaceDown = 0
    )
  )
)
