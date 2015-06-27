// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 4
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0
 *   Similar to (like): deuces
 *   Low card (lowpip): 11 (J)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object JacksInTheBox extends GameRules(
  id = "jacksinthebox",
  title = "Jacks in the Box",
  like = Some("deuces"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/jacks_in_the_box.htm")),
  description = "A variation on ^deuces^ which has fewer tableau piles but adds some cells.",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Jack
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
