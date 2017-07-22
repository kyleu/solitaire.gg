package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Grandfather extends GameRules(
  id = "grandfather",
  completed = true,
  title = "Grandfather",
  related = Seq("father"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/grandfather.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/grandfather.htm")
  ),
  layout = "::::sw|.f:f|2t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
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
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Waste),
      maxCards = 2
    )
  )
)
