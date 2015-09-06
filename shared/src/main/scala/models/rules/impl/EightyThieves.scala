// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 4 (4 decks)
 */
object EightyThieves extends GameRules(
  id = "eightythieves",
  completed = false,
  title = "Eighty Thieves",
  like = Some("fortythieves"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/eighty_thieves.htm")),
  description = "A very difficult four-deck version of ^fortythieves^ by Thomas Warfield. Needs a large screen.",
  layout = "swf|.::::t",
  deckOptions = DeckOptions(
    numDecks = 4
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
