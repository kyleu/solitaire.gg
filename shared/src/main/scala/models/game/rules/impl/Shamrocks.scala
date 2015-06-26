// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Fan
 *   Tableau initial cards (T0d): -2 (custom)
 *   Tableau action during deal (T0dd): 1 (Move kings to stack bottoms)
 *   Custom initial cards (T0ds): UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU UUU U
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Maximum cards per tableau (T0m): 3 (3 cards)
 *   Tableau piles (T0n): 18
 *   Tableau rank match rule for building (T0r): 160 (Build up or down)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 */
object Shamrocks extends GameRules(
  id = "shamrocks",
  title = "Shamrocks",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shamrocks.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Shamrocks"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/shamrocks.htm"),
    Link("Shamrocks Solitaire.com", "www.shamrockssolitaire.com/ShamrocksSolitaireInstructions.html")
  ),
  description = "A variation of ^fan^ where you can build up or down regardless of suit, but are limited to three cards per stack.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      maxCards = 3,
      actionDuringDeal = PileAction.MoveKingsToBottom
    )
  )
)
