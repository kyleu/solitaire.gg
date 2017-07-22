package models.rules.impl

import models.card.Rank
import models.rules._

object Floradora extends GameRules(
  id = "floradora",
  completed = false,
  title = "Floradora",
  like = Some("takingsilk"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/floradora.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/floradora.htm")
  ),
  layout = "swff|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      name = "Main Foundation",
      numPiles = 8,
      maxCards = 12,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
