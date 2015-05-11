// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Auto-move cards to foundation (F1a): 0 (Never)
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   TODO (F1u): 2
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 15
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Fifteen extends GameRules(
  id = "fifteen",
  title = "Fifteen",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fifteen.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#fifteen")
  ),
  description = "Build up on one foundation, down on the other. Build up or down on the tableau.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      wrapFromKingToAce = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
