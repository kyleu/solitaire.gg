package models.rules.impl

import models.rules._

object SeaTowers extends GameRules(
  id = "seatowers",
  completed = true,
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
  layout = "f::c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  ),
  cells = Some(CellRules(initialCards = 2))
)
