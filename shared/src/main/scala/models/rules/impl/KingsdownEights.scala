package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Reserve initial cards (R0d): 4
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 8
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealchunk): 4 (Four at a time)
 *   Deal cards from stock (dealto): 5
 *   Number of decks (ndecks): 2 (2 decks)
 *   *unused (unused): temp_hack
 */
object KingsdownEights extends GameRules(
  id = "kingsdowneights",
  completed = false,
  title = "Kingsdown Eights",
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/Kingsdown-Eights.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/kingsdown_eights.htm")
  ),
  layout = "sf|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Reserve, maximumDeals = Some(1), cardsDealt = StockCardsDealt.Count(4))),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 8, initialCards = 4))
)
