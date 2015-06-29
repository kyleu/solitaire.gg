// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): napoleonssquare
 */
object Blockade extends GameRules(
  id = "blockade",
  completed = true,
  title = "Blockade",
  related = Seq("napoleonssquare"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/blockade.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/blockade.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/blockade.php"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Blockade_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/blockade.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/blockade.htm")
  ),
  description = "A simple game that starts slow and ends with a flourish.",
  layout = Some(":s:f|t"),
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
