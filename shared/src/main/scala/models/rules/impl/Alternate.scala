package models.rules.impl

import models.card.Color
import models.pile.set.PileSet
import models.rules._

object Alternate extends GameRules(
  id = "alternate",
  completed = true,
  title = "Alternate",
  like = Some("sirtommy"),
  links = Seq(
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/alternate.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/alternate.php")
  ),
  layout = "sff|.t",
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = Seq(
    FoundationRules(
      name = "Red Foundation",
      numPiles = 2,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Color.Red)),
      initialCards = 2,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Black Foundation",
      setNumber = 1,
      numPiles = 2,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(Color.Black)),
      initialCards = 2,
      suitMatchRule = SuitMatchRule.AlternatingColors,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Stock),
      mayMoveToEmptyFrom = Seq(PileSet.Behavior.Stock)
    )
  )
)
