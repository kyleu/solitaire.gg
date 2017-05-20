package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Anubis extends GameRules(
  id = "anubis",
  completed = false,
  title = "Anubis",
  like = Some("doublepyramid"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/anubis.htm")),
  layout = "swf|p",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToThirteenOrK,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules(numPiles = 3)),
  foundations = Seq(FoundationRules(canMoveFrom = FoundationCanMoveFrom.Never, visible = false, autoMoveCards = true)),
  pyramids = Seq(
    PyramidRules(
      height = 9,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = PileSet.Behavior.wtpf,
      mayMoveToEmptyFrom = PileSet.Behavior.wtpf
    )
  )
)
