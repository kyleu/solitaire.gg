package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 5 (When stackable cards are removable)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   *T0db (T0db): 0
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Related games (related): sixbyfour, ephemeralfreecell, challengefreecell, antares, sevenbyfive, spidercel...
 *   Enable super moves, whatever those are (supermoves): 1
 */
object FreeCell extends GameRules(
  id = "freecell",
  completed = true,
  title = "FreeCell",
  related = Seq(
    "sixbyfour", "ephemeralfreecell", "challengefreecell", "antares", "sevenbyfive", "spidercells", "bigfreecell", "chinesefreecell",
    "sevenbyfour", "invertedfreecell", "selectivefreecell", "doublefreecell", "freecellduplex"
  ),
  links = Seq(
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/FreeCell"),
    Link("FreeCell Solitaire", "www.solitairecentral.com/articles/FreeCellSolitaireAWinningStrategy.html")
  ),
  layout = "c:f|.t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
