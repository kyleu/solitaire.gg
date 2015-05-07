// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

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
  description = "Invented by Paul Alfille, made famous by Microsoft, this game provide four temporary storage cells that can be used to move cards " +
    "around.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  cells = Some(CellRules()),
  complete = false
)

