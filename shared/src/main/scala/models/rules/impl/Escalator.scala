package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Escalator extends GameRules(
  id = "escalator",
  completed = true,
  title = "Escalator",
  like = Some("golf"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pyramid_golf.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Escalator.html.en")
  ),
  layout = ":s:::f|p",
  victoryCondition = VictoryCondition.NoneInPyramid,
  stock = Some(StockRules(cardsShown = 16, dealTo = StockDealTo.Foundation, maximumDeals = Some(1))),
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
  pyramids = Seq(
    PyramidRules(
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)
