// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 */
object LittleGiant extends GameRules(
  id = "littlegiant",
  title = "Little Giant",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_giant.htm")),
  description = "A one-deck version of Giant.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
