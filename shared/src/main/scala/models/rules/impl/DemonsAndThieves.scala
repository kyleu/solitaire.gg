// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 4 (Keeping piles level)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Tableau name (T0Nm): Left Tableau
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   *T0sc (T0sc): false
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Tableau name (T1Nm): Right Tableau
 *   Tableau initial cards (T1d): 8 (8 cards)
 *   Tableau piles (T1n): 5
 *   Tableau suit match rule for building (T1s): 1 (In same suit)
 *   *T1sc (T1sc): false
 *   Tableau suit match rule for moving stacks (T1ts): 1 (In same suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object DemonsAndThieves extends GameRules(
  id = "demonsandthieves",
  completed = false,
  title = "Demons and Thieves",
  like = Some("canfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demons_and_thieves.htm")),
  description = "In this game, the tableau is split into two halves, one half where you play by ^canfield^ rules, and one half where you play by ^f" +
    "ortythieves^ rules.",
  layout = "swf|r|tt",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 5,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)