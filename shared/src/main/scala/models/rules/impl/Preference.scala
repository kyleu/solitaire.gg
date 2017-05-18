package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): fortunesfavor
 *   Number of decks (ndecks): 1 (1 deck)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Preference extends GameRules(
  id = "preference",
  completed = true,
  title = "Preference",
  like = Some("fortunesfavor"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/preference.htm")),
  layout = "sw:f|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
