package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): fortress, citadel, castlemount, castleofindolence, streetsandalleys, selectiveca...
 *   Enable super moves, whatever those are (supermoves): 1
 */
object BeleagueredCastle extends GameRules(
  id = "beleagueredcastle",
  completed = true,
  title = "Beleaguered Castle",
  related = Seq("fortress", "citadel", "castlemount", "castleofindolence", "streetsandalleys", "selectivecastle"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Beleaguered_Castle"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/beleaguered_castle.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/beleaguered_castle.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Beleaguered_Castle.html.en")
  ),
  description = "A challenging game with simple rules. All cards start dealt face up and you build down regardless of suit, moving only single card" +
    "s. Somewhat similar to ^bakersdozen^.",
  layout = "::f|t",
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
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
