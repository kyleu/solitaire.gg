package models.rules.impl

import models.rules._

object Chequers extends GameRules(
  id = "chequers",
  completed = false,
  title = "Chequers",
  like = Some("caprice"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chequers.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/chequers.htm")
  ),
  layout = "ff|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
      name = "King Foundation",
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
      numPiles = 25,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 4, cardsFaceDown = -1))
)
