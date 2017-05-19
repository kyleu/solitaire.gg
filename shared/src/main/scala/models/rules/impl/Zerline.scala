package models.rules.impl

import models.card.Rank
import models.rules._

object Zerline extends GameRules(
  id = "zerline",
  completed = true,
  title = "Zerline",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/zerline.htm")),
  layout = "swf|.t:t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.King),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.HighRank
    ),
    TableauRules(
      name = "Reserve",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("tableau"),
      maxCards = 4
    )
  )
)
