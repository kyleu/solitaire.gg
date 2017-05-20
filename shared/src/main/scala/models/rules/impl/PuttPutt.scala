package models.rules.impl

import models.rules._

object PuttPutt extends GameRules(
  id = "puttputt",
  completed = true,
  title = "Putt Putt",
  like = Some("golf"),
  related = Seq("lincolngreens"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/putt_putt.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/PuttPutt.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Puttputt.htm"),
    Link("Jan Wolter's Experiments", "/article/golf.html")
  ),
  layout = "s:::::f|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  stock = Some(
    StockRules(
      cardsShown = 16,
      dealTo = StockDealTo.Foundation,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      lowRank = FoundationLowRank.AnyCard,
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
