package models.rules.impl

import models.rules._

object Twenty extends GameRules(
  id = "twenty",
  completed = true,
  title = "Twenty",
  related = Seq("colorado"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/twenty.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/twenty.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/twenty.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Twenty.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/twenty.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fox.html")
  ),
  layout = "sf:f|2t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
