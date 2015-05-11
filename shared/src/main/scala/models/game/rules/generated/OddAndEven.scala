// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Foundation rank match rule (F0r): 256 (Build up by 2)
 *   TODO (F0u): 2
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 2 (2)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 256 (Build up by 2)
 *   TODO (F1u): 2
 *   Foundation Sets (Fn): 2
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): royalcotillion
 */
object OddAndEven extends GameRules(
  id = "oddandeven",
  title = "Odd and Even",
  related = Seq("royalcotillion"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Odd_and_Even"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/odd_and_even.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/odd_and_even.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/OddandEven.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/odd_and_even.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/oddandeven.html"),
    Link("Lesey Bolton on netplaces.com", "www.netplaces.com/games/solitary-card-games/odd-and-even.htm"),
    Link("Dick's Games of Solitaire (1898)", "howtoplaysolitaire.blogspot.com/2010/06/odd-and-even-double-deck-solitaire-game.html")
  ),
  description = "A difficult, old and remarkably stupid game where foundation piles are built up by twos and no building is allowed on the tableau.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
