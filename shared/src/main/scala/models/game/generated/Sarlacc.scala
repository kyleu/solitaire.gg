package models.game.generated

import models.game._
import models.game.rules._

// scalastyle:off
object Sarlacc extends GameRules(
  id = "sarlacc",
  title = "Sarlacc",
  description = "A ^freecell^ variant with a tableau of interlocking columns.",
  victoryCondition = VictoryCondition.AllOnFoundation,
  cardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
  deckOptions = DeckOptions(
    numDecks = 1,
    suits = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs),
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace),
    lowRank = Some(Rank.Ace)
  ),
  stock = None,
  waste = None,
  foundations = Seq(
    FoundationRules(
      name = "Foundation",
      numPiles = 4,
      lowRank = FoundationLowRank.DeckLowRank,
      initialCards = InitialCards.Count(0),
      suitMatchRule = SuitMatchRule.SameSuit,
      rankMatchRule = RankMatchRule.Up,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = false,
      maxCards = -1,
      canMoveFrom = FoundationCanMoveFrom.Never,
      mayMoveToFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),
      offscreen = false,
      autoMoveCards = true,
      autoMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
    )
  ),
  tableaus = Nil,
  cells = Some(
    CellRules(

      name = "Cell",

      pluralName = "Cells",

      numPiles = 6,

      canMoveFrom = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau"),

      initialCards = 0,

      numEphemeral = 0

    )
  ),
  reserves = None,
  pyramids = Seq(
    PyramidRules(
      name = "Tableau",
      pyramidType = PyramidType.Standard,
      height = 7,
      cardsFaceDown = PyramidFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.AlternatingColors,
      rankMatchRuleForBuilding = RankMatchRule.Down,
      wrapFromKingToAce = false,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      rankMatchRuleForMovingStacks = RankMatchRule.Down,
      mayMoveToNonEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      mayMoveToEmptyFrom = Seq("Waste", "Tableau", "Pyramid", "Foundation"),
      emptyFilledWith = PyramidFillEmptyWith.Aces
    )
  )
)
// scalastyle:on

