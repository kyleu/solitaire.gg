package models.rules.impl

import models.rules._

object German extends GameRules(
  id = "german",
  completed = false,
  title = "German",
  related = Seq("bavarian"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/German_Patience"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/german_patience.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/german_patience.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/german-patience.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/GermanPatience.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/german_patience.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/german_patience.html")
  ),
  layout = "sw|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
