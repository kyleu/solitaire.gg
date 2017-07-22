package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Diplomat extends GameRules(
  id = "diplomat",
  completed = true,
  title = "Diplomat",
  like = Some("congress"),
  related = Seq("rowsoffour"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Diplomat_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/diplomat.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Diplomat.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/diplomat.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Diplomat.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/diplomat.php"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/diplomat.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/diplomat.html")
  ),
  layout = "swf|.:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  )
)
