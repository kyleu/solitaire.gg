// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): busyaces
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): preference
 *   Enable super moves, whatever those are (supermoves): 1
 */
object FortunesFavor extends GameRules(
  id = "fortunesfavor",
  title = "Fortune's Favor",
  like = Some("busyaces"),
  related = Seq("preference"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Fortune's_Favor"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fortunes_favor.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fortunes_favor.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/FortunesFavor.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fortune_s_favor.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fortunes_favor.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/fortunes_favor.htm")
  ),
  description = "An extremely easy, one-deck version of ^busyaces^.",
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
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
