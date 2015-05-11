// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DU UDU DUDU UDUDU DUDUDU UDUDUDU DUDUDU UDUDU DUDU UDU DU U
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Cleopatra extends GameRules(
  id = "cleopatra",
  title = "Cleopatra",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cleopatra.htm")),
  description = "Thomas Warfield's variant of ^fortythieves^ with a pyramid-shaped tableau.",
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
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "UDU",
        "DUDU",
        "UDUDU",
        "DUDUDU",
        "UDUDUDU",
        "DUDUDU",
        "UDUDU",
        "DUDU",
        "UDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
