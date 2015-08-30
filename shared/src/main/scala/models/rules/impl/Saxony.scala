// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 4 (4 cards)
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Reserve name (R0Nm): Tableau
 *   Reserve initial cards (R0d): 1
 *   Number of reserve piles (R0n): 8
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 5
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Saxony extends GameRules(
  id = "saxony",
  completed = false,
  title = "Saxony",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/saxony.htm")),
  description = "You have four cells, four reserve piles where you can build down in suit, and eight tableau piles, where cards are dealt, but no b" +
    "uilding is allowed.",
  layout = "sf|r|c|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Reserve,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 4
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Tableau",
      numPiles = 8,
      initialCards = 1,
      cardsFaceDown = -1
    )
  )
)