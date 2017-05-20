package models.rules.impl

import models.pile.set.PileSet
import models.rules._

object Colorado extends GameRules(
  id = "colorado",
  completed = false,
  title = "Colorado",
  like = Some("twenty"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Colorado_(game)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/colorado.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/colorado.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/colorado.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/colorado.php"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Colorado.shtml"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/colorado.htm")
  ),
  layout = "sff|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.Manually, maximumDeals = Some(1))),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq(PileSet.Behavior.Stock)
    )
  )
)
