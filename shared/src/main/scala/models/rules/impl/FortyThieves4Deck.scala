package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 14
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 4 (4 decks)
 */
object FortyThieves4Deck extends GameRules(
  id = "fortythieves4",
  completed = true,
  title = "Forty Thieves (4 deck)",
  like = Some("fortythieves"),
  links = Seq(Link("Solsuite Solitaire", "www.solsuite.com/games/forty_thieves_four_decks.htm")),
  layout = "swf|.::t",
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
      numPiles = 14,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
