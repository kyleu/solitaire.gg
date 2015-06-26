// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Reserve name (R0Nm): Storehouse
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): canfield
 *   Low card (lowpip): 2 (2)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Related games (related): doublestorehouse
 */
object Storehouse extends GameRules(
  id = "storehouse",
  title = "Storehouse",
  like = Some("canfield"),
  related = Seq("doublestorehouse"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/storehouse.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/storehouse.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/storehouse.htm"),
    Link("Swoop Solitaire", "www.swoopsoftware.com/solitaire_rules/storehouse.html"),
    Link("An 1898 description", "howtoplaysolitaire.blogspot.com/2010/06/storehouse-single-deck-solitaire-game.html"),
    Link("Jan Wolter's Experiments", "/article/storehouse.html")
  ),
  description = "A old ^canfield^ variant first described in 1939. A pleasant game, but there is scarcely any strategy required.",
  deckOptions = DeckOptions(
    lowRank = Rank.Two
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Storehouse",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)
