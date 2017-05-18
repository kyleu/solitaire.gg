package models.rules.impl

import models.card.Rank
import models.rules._

object RoyalRendezvous extends GameRules(
  id = "royalrendezvous",
  completed = true,
  title = "Royal Rendezvous",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Royal_Rendezvous"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/royal_rendezvous.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/royal_rendezvous.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/RoyalRendezvous.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/royal_rendezvous.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/royal-rendezvous.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RoyalRendezvous.htm")
  ),
  layout = "sw:ff|::::ff|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Straight Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4
    ),
    FoundationRules(
      name = "Odd Foundation",
      setNumber = 1,
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
    ),
    FoundationRules(
      name = "Even Foundation",
      setNumber = 2,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6
    ),
    FoundationRules(
      name = "King Foundation",
      setNumber = 3,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      maxCards = 1
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
