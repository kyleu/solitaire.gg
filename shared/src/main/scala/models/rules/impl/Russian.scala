package models.rules.impl

import models.rules._

object Russian extends GameRules(
  id = "russian",
  completed = true,
  title = "Russian",
  related = Seq("doublerussian", "triplerussian", "ukrainian", "russiancell", "odessa", "tenacross"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/russian_solitaire.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Russian%20Solitaire.shtml"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/russian_solitaire.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RussianSolitaire.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/russian-solitaire.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/russian_solitaire.htm")
  ),
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
