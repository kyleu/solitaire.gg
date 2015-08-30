// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Card initially dealt into cells (C0d): 2 (2 cards)
 *   Number of cells (C0n): 4
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object SeaTowers extends GameRules(
  id = "seatowers",
  completed = false,
  title = "Sea Towers",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sea_towers.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Seahaven_Towers_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/sea-towers.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/seahaven_towers.html"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/SeaTowersSolitaire.shtml"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/sea_towers.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/sea_towers.htm"),
    Link("Super Solitaire", "supersolitaire.weisswo.com/Games+Rules/Entries/2010/11/12_Seahaven_Towers.html"),
    Link("Bicycle", "www.bicyclecards.com/card-games/rule/seahaven-towers")
  ),
  description = "A popular ^freecell^ variation invented in 1988 by Art Cabral. The initial layout is different, and we must build down in suit ins" +
    "tead of in alternating colors.",
  layout = "f|c|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(
    CellRules(
      initialCards = 2
    )
  )
)