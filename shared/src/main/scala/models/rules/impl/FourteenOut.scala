package models.rules.impl

import models.rules._

object FourteenOut extends GameRules(
  id = "fourteenout",
  completed = true,
  title = "Fourteen Out",
  related = Seq("juvenile", "doublefourteens", "triplefourteens", "tensout"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fourteen_out.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fourteen_out.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/block_fourteen.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/take_fourteen.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Fourteen.html.en")
  ),
  layout = "2tf",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToFourteen,
  foundations = Seq(
    FoundationRules(
      canMoveFrom = FoundationCanMoveFrom.Never,
      visible = false,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
