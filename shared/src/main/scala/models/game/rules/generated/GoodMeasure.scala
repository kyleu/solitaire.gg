// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 2 (2 cards)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau action during deal (T0dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau action during deal (T1dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 6
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 1 (1 tableau set)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): bakersdozen
 *   Related games (related): spanish, goodmeasure
 */
object GoodMeasure extends GameRules(
  id = "goodmeasure",
  title = "Good Measure",
  like = Some("bakersdozen"),
  related = Seq("spanish", "goodmeasure"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/good_measure.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/good_measure.htm"),
    Link("About.com (Erik Arneson)", "boardgames.about.com/od/solitaire/a/good_measure.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/GoodMeasure.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/good_measure.html")
  ),
  description = "A much more difficult variation of ^bakersdozen^ with fewer tableau piles.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 2,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
