// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): alibaba
 *   Maximum deals from stock (maxdeals): 0
 */
object Cassim extends GameRules(
  id = "cassim",
  completed = true,
  title = "Cassim",
  like = Some("alibaba"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cassim.htm")),
  description = "A version of ^alibaba^ with a smaller tableau and an infinity of redeals. In the <em>One Thousand and One Nights</em> Cassim was A" +
    "li Baba's brother and the leader of the ^fortythieves^.",
  stock = Some(StockRules()),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
