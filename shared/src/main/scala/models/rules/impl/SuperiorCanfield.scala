package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 */
object SuperiorCanfield extends GameRules(
  id = "superiorcanfield",
  completed = false,
  title = "Superior Canfield",
  like = Some("canfield"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/superior_canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SuperiorCanfield.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/superior_canfield.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/superior-canfield.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/superior_canfield.php")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)
