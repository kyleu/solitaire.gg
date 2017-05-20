package models.rules.impl

import models.rules._

object Shamrocks extends GameRules(
  id = "shamrocks",
  completed = false,
  title = "Shamrocks",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shamrocks.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Shamrocks"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/shamrocks.htm"),
    Link("Shamrocks Solitaire.com", "www.shamrockssolitaire.com/ShamrocksSolitaireInstructions.html")
  ),
  layout = "f|t",
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
