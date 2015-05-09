// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 12
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 3
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Related games (related): rainbow, storehouse, acme, canfieldgallery, superiorcanfield, canfieldrush, demo...
 */
object Chameleon extends GameRules(
  id = "chameleon",
  title = "Chameleon",
  like = Some("canfield"),
  related = Seq("rainbow", "storehouse", "acme", "canfieldgallery", "superiorcanfield", "canfieldrush", "demonsandthieves", "chameleon"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chameleon.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Chameleon.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Canfield_%28solitaire%29")
  ),
  description = "In case ^canfield^ wasn't hard enough for you, here's a version with only three tableau piles. But you can stack cards regardless " +
    "of suit, so it'll be OK. Occasionally.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 12,
      cardsFaceDown = 100
    )
  )
)
