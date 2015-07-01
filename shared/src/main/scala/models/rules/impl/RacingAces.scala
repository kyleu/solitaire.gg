// Generated rules for Solitaire.gg.
package models.rules.impl

import models.game._
import models.rules._

object RacingAces extends GameRules(
  id = "racingaces",
  completed = true,
  title = "Racing Aces",
  like = Some("acesandkings"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/racing_aces.htm")),
  description = "A three-deck version of ^acesandkings^ invented by Thomas Warfield.",
  layout = Some("sw|ff|ff|:t|::.r"),
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any
    ),
    FoundationRules(
      name = "Sixes Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Six),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down,
      maxCards = 6
    ),
    FoundationRules(
      name = "Sevens Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Seven),
      suitMatchRule = SuitMatchRule.Any,
      maxCards = 7
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Down
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 3,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)
