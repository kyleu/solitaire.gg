// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): lower48
 */
object FortyAndEight extends GameRules(
  id = "fortyandeight",
  title = "Forty and Eight",
  related = Seq("lower48"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_and_eight.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/forty_and_eight.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/forty_and_eight.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/forty-and-eight.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FortyandEight.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/forty_and_eight.htm")
  ),
  description = "Two decks, forty cards in the tableau, eight foundation piles, building down in the same suit. You can only move single cards. Oft" +
    "en it feels like nothing is happening for a long time, and then the game works out after all. A good game for making you feel smar" +
    "t.",
  layout = Some("sw|f|t"),
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
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
