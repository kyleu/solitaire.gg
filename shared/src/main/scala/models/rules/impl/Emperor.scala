package models.rules.impl

import models.rules._

object Emperor extends GameRules(
  id = "emperor",
  completed = false,
  title = "Emperor",
  like = Some("rankandfile"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Emperor_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/emperor.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/emperor.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/emperor.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/emperor.htm"),
    Link("Bicycle", "www.bicyclecards.ca/game-rules/emperor/184.php?page_id=32")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 10, initialCards = InitialCards.Count(4), suitMatchRuleForMovingStacks = SuitMatchRule.None))
)
