package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object ShadyLanes extends GameRules(
  id = "shadylanes",
  completed = false,
  title = "Shady Lanes",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/shady_lanes.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/shady_lanes.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/shady-lanes.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ShadyLanes.htm")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Reserve)
    )
  ),
  reserves = Some(ReserveRules(numPiles = 4, cardsFaceDown = -1))
)
