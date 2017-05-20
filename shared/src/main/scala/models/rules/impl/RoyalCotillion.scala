package models.rules.impl

import models.card.Rank
import models.rules._

object RoyalCotillion extends GameRules(
  id = "royalcotillion",
  completed = false,
  title = "Royal Cotillion",
  like = Some("oddandeven"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Royal_Cotillion"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_cotillion.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-cotillion.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/royal_cotillion.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RoyalCotillion.htm")
  ),
  layout = "swff|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 4, initialCards = 3))
)
