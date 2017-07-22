package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Frog extends GameRules(
  id = "frog",
  completed = true,
  title = "Frog",
  related = Seq("fly"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Frog_(game)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/frog.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Frog.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/frog.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/frog.htm")
  ),
  layout = "sf|r:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
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
  reserves = Some(ReserveRules(name = "Frog", initialCards = 13))
)
