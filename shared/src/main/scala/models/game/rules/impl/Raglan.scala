// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUU UUUUUUU UUUUUUU UUUUUU UUUUU UUUU UUU UU U
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Similar to (like): kingalbert
 *   Enable super moves, whatever those are (supermoves): 1
 *   *unused (unused): temp_hack
 */
object Raglan extends GameRules(
  id = "raglan",
  title = "Raglan",
  like = Some("kingalbert"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/raglan.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/raglan.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/raglan.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Raglan.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/raglan.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/raglan.htm")
  ),
  description = "This is ^kingalbert^ with a different tableau and Aces already on the foundation. It is a bit easier to solve.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
