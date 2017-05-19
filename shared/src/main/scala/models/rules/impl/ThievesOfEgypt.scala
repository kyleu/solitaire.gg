package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U UUU UUUUU UUUUUUU UUUUUUUUU UUUUUUUUUU UUUUUUUU UUUUUU UUUU UU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object ThievesOfEgypt extends GameRules(
  id = "thievesofegypt",
  completed = true,
  title = "Thieves of Egypt",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thieves_of_egypt.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/thieves_of_egypt.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/thieves_of_egypt.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thieves-of-egypt.htm")
  ),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UUU",
        "UUUUU",
        "UUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUUUU",
        "UUUUUUUU",
        "UUUUUU",
        "UUUU",
        "UU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
