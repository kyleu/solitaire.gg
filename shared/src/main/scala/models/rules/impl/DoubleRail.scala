package models.rules.impl

import models.rules._

object DoubleRail extends GameRules(
  id = "doublerail",
  completed = true,
  title = "Double Rail",
  like = Some("singlerail"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/double_rail.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/double_rail.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/double_rail.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/double-rail.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/DoubleRail.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/double_rail.htm")
  ),
  layout = "swf|:::t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 5,
    initialCards = InitialCards.Count(1),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.Any
  ))
)
