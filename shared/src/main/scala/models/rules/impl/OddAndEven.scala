package models.rules.impl

import models.card.Rank
import models.rules._

object OddAndEven extends GameRules(
  id = "oddandeven",
  completed = false,
  title = "Odd and Even",
  related = Seq("royalcotillion"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Odd_and_Even"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/odd_and_even.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/odd_and_even.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/OddandEven.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/odd_and_even.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/oddandeven.html"),
    Link("Lesey Bolton on netplaces.com", "www.netplaces.com/games/solitary-card-games/odd-and-even.htm"),
    Link("Dick's Games of Solitaire (1898)", "howtoplaysolitaire.blogspot.com/2010/06/odd-and-even-double-deck-solitaire-game.html")
  ),
  layout = "swf:f|.:t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.SpecificRank(Rank.Two),
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.UpBy2
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
