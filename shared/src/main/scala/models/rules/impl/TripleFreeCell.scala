package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 10
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 12 (12 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): celleleven, freecellthreedeck
 *   Enable super moves, whatever those are (supermoves): 1
 */
object TripleFreeCell extends GameRules(
  id = "triplefreecell",
  completed = true,
  title = "Triple FreeCell",
  related = Seq("celleleven", "freecellthreedeck"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/triple_freecell.htm")),
  layout = ".f|:.c|t",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(12),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 10
    )
  )
)
