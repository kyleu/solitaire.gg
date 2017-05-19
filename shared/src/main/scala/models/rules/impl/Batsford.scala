package models.rules.impl

import models.rules._

object Batsford extends GameRules(
  id = "batsford",
  completed = true,
  title = "Batsford",
  related = Seq("batsfordagain"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/batsford.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Batsford_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/batsford.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/batsford.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Batsford.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/batsford.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/batsford.html")
  ),
  layout = "swf|tt",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      emptyFilledWith = FillEmptyWith.HighRank
    ),
    TableauRules(
      name = "Reserve",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Equal,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank,
      maxCards = 3
    )
  )
)
