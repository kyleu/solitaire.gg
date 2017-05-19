package models.rules.impl

import models.rules._

object BuffaloBill extends GameRules(
  id = "buffalobill",
  completed = true,
  title = "Buffalo Bill",
  related = Seq("littlebillie"),
  links = Seq(
    Link("David Parlett's Page", "www.davpar.eu/patience/buffbill.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/buffalo_bill.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/buffalo_bill.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/buffalo-bill.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/buffalo_bill.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BuffaloBill.htm")
  ),
  layout = ":.f::f|::.c|2t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Fan",
      numPiles = 26,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(CellRules(name = "Reserve", pluralName = "Reserve", numPiles = 8))
)
