// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 */
object FamousFifty extends GameRules(
  id = "famousfifty",
  title = "Famous Fifty",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/famous_fifty.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/famous-fifty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FamousFifty.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/famous_fifty.htm")
  ),
  description = "A difficult ^fortythieves^ variation which starts with one extra card on each tableau pile.",
  deckOptions = DeckOptions(
    numDecks = 2
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
