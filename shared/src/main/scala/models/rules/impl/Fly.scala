package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Fly extends GameRules(
  id = "fly",
  completed = true,
  title = "Fly",
  like = Some("frog"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fly.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fly.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fly.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fly.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/fly.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Frog_(game)")
  ),
  layout = "sf|r.:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Stock),
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock)
    )
  ),
  reserves = Some(ReserveRules(name = "Fly", initialCards = 13))
)
