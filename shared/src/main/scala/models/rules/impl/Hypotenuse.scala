package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDDDDDDU DDDDDDDDU DDDDDDDU DDDDDDU DDDDDU DDDDU DDDU DDU DU U
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Similar to (like): eternaltriangle
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Hypotenuse extends GameRules(
  id = "hypotenuse",
  completed = false,
  title = "Hypotenuse",
  like = Some("eternaltriangle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/hypotenuse.htm")),
  description = "A version of ^eternaltriangle^ with some cards face down.",
  layout = "sf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDDDU",
        "DDDDDDDDU",
        "DDDDDDDU",
        "DDDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDU",
        "DDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
