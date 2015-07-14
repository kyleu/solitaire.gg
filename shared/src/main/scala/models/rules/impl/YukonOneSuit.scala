// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Suit
import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DUUUUU DDUUUUU DDDUUUUU DDDDUUUUU DDDDDUUUUU DDDDDDUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): yukon
 *   Number of decks (ndecks): 4 (4 decks)
 *   Custom suits (suits): 1 (S)
 */
object YukonOneSuit extends GameRules(
  id = "yukononesuit",
  completed = true,
  title = "Yukon One Suit",
  description = "A one-suit variation of ^yukon^. The game is almost always winnable, but still makes you think a bit.",
  layout = Some(":.f|t"),
  like = Some("yukon"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon_one_suit.htm")),
  deckOptions = DeckOptions(
    numDecks = 4,
    suits = Seq(Suit.Spades)
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
