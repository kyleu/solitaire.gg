package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Dieppe extends GameRules(
  id = "dieppe",
  completed = false,
  title = "Dieppe",
  like = Some("congress"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/dieppe.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/dieppe.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/dieppe.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/dieppe.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Dieppe.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/dieppe.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  )
)
