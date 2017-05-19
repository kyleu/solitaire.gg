package models.rules.impl

import models.rules._

object BusyAces extends GameRules(
  id = "busyaces",
  completed = true,
  title = "Busy Aces",
  related = Seq("stages", "courtyard", "dimes", "fortunesfavor", "deuces"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/busy_aces.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/busy_aces.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/busy_aces.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/busy_aces.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BusyAces.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/busy-aces.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/busy_aces.htm"),
    Link("Amelia Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/view-article/1866274/")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
