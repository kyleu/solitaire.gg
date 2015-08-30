// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Suit
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDDDDU UUUUUUU DDDDDDDU UUUUUUU DDDDDDDU UUUUUUU DDDDDDDU UUUUUUU DDDDDDDU DDD...
 *   Tableau piles (T0n): 18
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 2 (2 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Custom suits (suits): 1011
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object MondoSpider extends GameRules(
  id = "mondospider",
  completed = false,
  title = "Mondo Spider",
  like = Some("spider"),
  description = "A rather arduous double-size eight-suit ^spider^ variant.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs, Suit.Horseshoes, Suit.Stars, Suit.Tridents, Suit.Moons)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)