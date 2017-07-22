package models.rules.impl

import models.rules._

object LittleBillie extends GameRules(
  id = "littlebillie",
  completed = true,
  title = "Little Billie",
  like = Some("buffalobill"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_billie.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/little_billee.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleBillie.htm")
  ),
  layout = "::ff|::c|2t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
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
  tableaus = IndexedSeq(
    TableauRules(
      name = "Fan",
      numPiles = 24,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  cells = Some(CellRules(name = "Reserve", pluralName = "Reserve", numPiles = 8, initialCards = 8)),
  special = Some(SpecialRules(redealsAllowed = 2))
)
