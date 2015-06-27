// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau action during deal (T0dd): 0 (Do nothing special)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau action during deal (T1dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 6
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): spanish
 *   Enable super moves, whatever those are (supermoves): 1
 */
object CastlesInSpain extends GameRules(
  id = "castlesinspain",
  title = "Castles in Spain",
  like = Some("spanish"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castles_in_spain.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/castles_in_spain.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/castles-in-spain.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/castles_in_spain.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/CastlesinSpain.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/castles_in_spain.htm")
  ),
  description = "A variant of ^bakersdozen^ that allows filling in spaces with any card and where we build in alternate colors.",
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
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 6,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
