// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Tableau name (T1Nm): Reserve
 *   Tableau initial cards (T1d): 0 (None)
 *   Empty tableau is filled with (T1f): 1 (Kings only)
 *   Maximum cards per tableau (T1m): 3 (3 cards)
 *   Tableau piles (T1n): 1
 *   Tableau rank match rule for building (T1r): 64 (Build equal)
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): batsfordagain
 */
object Batsford extends GameRules(
  id = "batsford",
  title = "Batsford",
  related = Seq("batsfordagain"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/batsford.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Batsford_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/batsford.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/batsford.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Batsford.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/batsford.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/batsford.html")
  ),
  description = "A two-deck ^klondike^ game with a special reserve that can store up to three kings.",
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
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      emptyFilledWith = FillEmptyWith.HighRank
    ),
    TableauRules(
      name = "Reserve",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      maxCards = 3
    )
  )
)
