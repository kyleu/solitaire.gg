package models.rules.impl

import models.rules._

object Nationale extends GameRules(
  id = "nationale",
  completed = false,
  title = "Nationale",
  like = Some("caprice"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/nationale.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/nationale.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/nationale.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Nationale.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/nationale.php")
  ),
  layout = "ff|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Ace Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "King Foundation",
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
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionDuringDeal = PileAction.MoveToFoundation
    )
  )
)
