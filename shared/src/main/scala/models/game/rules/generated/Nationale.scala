// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Ace Foundation
 *   Foundation initial cards (F0d): -1
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Foundation name (F1Nm): King Foundation
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): -1
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   TODO (F1u): 2
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Tableau action during deal (T0dd): 3 (Move cards to foundations)
 *   Tableau piles (T0n): 12
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): caprice
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): chequers, cicely, nationale
 */
object Nationale extends GameRules(
  id = "nationale",
  title = "Nationale",
  like = Some("caprice"),
  related = Seq("chequers", "cicely", "nationale"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/nationale.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/nationale.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/nationale.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Nationale.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/nationale.php")
  ),
  description = "Like ^caprice^ without a stock.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      initialCards = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
