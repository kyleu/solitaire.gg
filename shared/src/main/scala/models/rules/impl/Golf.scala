package models.rules.impl

import models.rules._

object Golf extends GameRules(
  id = "golf",
  completed = true,
  title = "Golf",
  related = Seq("allinarow", "escalator", "puttputt", "panthercreek", "golfrush"),
  links = Seq(
    Link("Solitaire Laboratory", "www.solitairelaboratory.com/golf.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Golf_(patience)"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Golf.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/golf.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/golf.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Golf.html.en"),
    Link("Jan Wolter's Experiments", "/article/golf.html"),
    Link("Dan Fletcher's Strategy Guide", "www.solitairecentral.com/articles/GolfSolitaireStrategyGuide.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/golf.htm")
  ),
  layout = "t|f:s",
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
      wrap = false,
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
