package models.rules.impl

import models.card.Suit
import models.rules._

object BinaryStar extends GameRules(
  id = "binarystar",
  completed = false,
  title = "Binary Star",
  like = Some("blackhole"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/binary_star.htm"),
    Link("Jan Wolter's Experiments", "/article/blackhole.html")
  ),
  layout = "ff|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  deckOptions = DeckOptions(numDecks = 2),
  foundations = IndexedSeq(
    FoundationRules(
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Hearts)),
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Spades)),
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 17,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
