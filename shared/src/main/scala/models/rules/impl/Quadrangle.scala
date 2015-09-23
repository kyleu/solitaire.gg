package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): corona
 *   Low card (lowpip): -2 (?)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Quadrangle extends GameRules(
  id = "quadrangle",
  completed = false,
  title = "Quadrangle",
  like = Some("corona"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/quadrangle.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/quadrangle.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/quadrangle.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/quadrangle.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/quadrangle.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Quadrangle.htm")
  ),
  description = "A variation of ^corona^ where the base card is determined by a card dealt into the foundation.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2,
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
      numPiles = 8,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
