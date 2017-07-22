package models.rules.impl

import models.card.Rank
import models.rules._

object MountOlympus extends GameRules(
  id = "mountolympus",
  completed = true,
  title = "Mount Olympus",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Mount_Olympus_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/mount_olympus.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/mount_olympus.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#mount")
  ),
  layout = "sf|:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 7,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCards = 8,
      rankMatchRule = RankMatchRule.UpBy2,
      maxCards = 6,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.DownBy2,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.DownBy2,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock
    )
  )
)
