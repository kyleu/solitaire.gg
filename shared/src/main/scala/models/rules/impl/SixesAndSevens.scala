package models.rules.impl

import models.card.Rank
import models.rules._

object SixesAndSevens extends GameRules(
  id = "sixesandsevens",
  completed = false,
  title = "Sixes and Sevens",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/sixes_and_sevens.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/sixes_and_sevens.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SixesandSevens.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/sixes-and-sevens.htm")
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
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Six),
      initialCards = 8,
      rankMatchRule = RankMatchRule.Down,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Seven),
      initialCards = 8,
      maxCards = 7,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 1
    )
  )
)
