// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Foundation wraps from king to ace (F0w): false
 *   *S0cardsShown (S0cardsShown): 16
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 7
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 6 (To all foundation piles)
 *   Left mouse interface function (leftfunc): 2
 *   Related games (related): allinarow, escalator, puttputt, panthercreek, golfrush
 *   Touch interface function (touchfunc): 2
 *   Victory condition (victory): 5 (All cards on foundation or stock)
 */
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
  description = "Build up or down on the single foundation to take cards off the tableau, where no building is allowed.",
  layout = Some("t|f:s"),
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
