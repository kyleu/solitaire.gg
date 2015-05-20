// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 21 (Deck's low card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Can move cards from foundation (F0mb): 1 (Always)
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Initial card restriction (F0u): 7 (Hearts)
 *   Foundation wraps from king to ace (F0w): true
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Foundation initial cards (F1d): 1 (1 cards)
 *   Maximum cards for foundation (F1m): 0
 *   Can move cards from foundation (F1mb): 1 (Always)
 *   Number of foundation piles (F1n): 1 (1 stack)
 *   Foundation rank match rule (F1r): 160 (Build up or down)
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Initial card restriction (F1u): 8 (Spades)
 *   Foundation wraps from king to ace (F1w): true
 *   Foundation Sets (Fn): 2
 *   *S0cardsShown (S0cardsShown): 16
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 17
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 6 (To all foundation piles)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): blackhole
 *   Number of decks (ndecks): 2 (2 decks)
 *   Touch interface function (touchfunc): 2
 *   Victory condition (victory): 5 (All cards on foundation or stock)
 */
object BinaryStar extends GameRules(
  id = "binarystar",
  title = "Binary Star",
  like = Some("blackhole"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/binary_star.htm"),
    Link("Jan Wolter's Experiments", "/article/blackhole.html")
  ),
  description = "Thomas Warfield's two-deck version of ^blackhole^ has two foundation piles.",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  foundations = Seq(
    FoundationRules(
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Hearts)),
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Spades)),
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      wrapFromKingToAce = true,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 17,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
