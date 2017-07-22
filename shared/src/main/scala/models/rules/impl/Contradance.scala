package models.rules.impl

import models.card.Rank
import models.rules._

object Contradance extends GameRules(
  id = "contradance",
  completed = false,
  title = "Contradance",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/contradance.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Contradance_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/contradance.htm")
  ),
  layout = "swff",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(2))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Five),
      rankMatchRule = RankMatchRule.Down,
      maxCards = 6,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 8,
      lowRank = FoundationLowRank.SpecificRank(Rank.Six),
      maxCards = 7,
      autoMoveCards = true
    )
  )
)
