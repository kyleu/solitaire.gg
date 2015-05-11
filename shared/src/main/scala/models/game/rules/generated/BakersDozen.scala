// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau action during deal (T0dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau action during deal (T1dd): 1 (Move kings to stack bottoms)
 *   Empty tableau is filled with (T1f): 5 (No card)
 *   Tableau piles (T1n): 6
 *   Tableau suit match rule for building (T1s): 5 (Regardless of suit)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Related games (related): spanish, goodmeasure
 */
object BakersDozen extends GameRules(
  id = "bakersdozen",
  title = "Baker's Dozen",
  related = Seq("spanish", "goodmeasure"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dozen.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bakers-dozen.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Baker's_Dozen_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bakers_dozen.html"),
    Link("About.com", "boardgames.about.com/od/solitaire/a/bakers_dozen.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/BakersDozen.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Bakers_Dozen.html.en")
  ),
  description = "Rearrange the thirteen tableau piles to free up cards for the foundation by moving one card at a time. Often winnable, but takes s" +
    "ome planning.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionDuringDeal = PileAction.MoveKingsToBottom
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
