package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U UU UUU UUUU UUUUU UUUUUU UUUUUUU UUUUUU UUUUU UUUU UUU UU U
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of decks (ndecks): 3 (3 decks)
 */
object Alexandria extends GameRules(
  id = "alexandria",
  completed = false,
  title = "Alexandria",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alexandria.htm")),
  description = "A three-deck version of ^thievesofegypt^ invented by Thomas Warfield.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
