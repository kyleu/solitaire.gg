// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): -1
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 */
object BoxKite extends GameRules(
  id = "boxkite",
  title = "Box Kite",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Box_Kite_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/box_kite.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/box_kite.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/box_kite.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/box-kite.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BoxKite.htm")
  ),
  description = "Tableaus build up or down, half the foundations build up, half build down.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
