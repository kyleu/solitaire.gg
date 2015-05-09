// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object LittleForty extends GameRules(
  id = "littleforty",
  title = "Little Forty",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_forty.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/little_forty.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleForty.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/LittleForty.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/little_forty.htm")
  ),
  description = "Like ^fortythieves^, but we build in regardless of color, can move sequences, and can make three passes through the deck, dealing " +
    "three cards at a time.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
