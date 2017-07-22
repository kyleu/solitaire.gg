package models.rules.impl

import models.card.Rank
import models.rules._

object Carousel extends GameRules(
  id = "carousel",
  completed = true,
  title = "Carousel",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/carousel.htm")),
  layout = "sw.f|f|f|t",
  deckOptions = DeckOptions(
    numDecks = 2,
    ranks = Seq(Rank.Two, Rank.Three, Rank.Four, Rank.Five, Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.Ace)
  ),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Ace Foundation",
      lowRank = FoundationLowRank.SpecificRank(Rank.Ace),
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Equal,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Evens Foundation",
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Odds Foundation",
      setNumber = 2,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Three),
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 5,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.Count(4),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.None,
    autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
  ))
)
