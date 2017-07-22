package models.rules.impl

import models.rules._

object Interchange extends GameRules(
  id = "interchange",
  completed = true,
  title = "Interchange",
  related = Seq("tripleinterchange", "breakwater", "unlimited"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/interchange.htm"),
    Link("The Dao of Interchange", "www.goodsol.com/games/interchange-dao.html"),
    Link("Michael Smoker's very confusing description", "www.hobbyhub360.com/index.php/interchange-solitaire-card-game-8745/")
  ),
  layout = "swf|::t",
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
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
