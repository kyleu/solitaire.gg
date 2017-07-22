package models.rules.impl

import models.rules._

object Australian extends GameRules(
  id = "australian",
  completed = false,
  title = "Australian",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Australian_Patience_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/australian_patience.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/australian_patience.html"),
    Link("Games for One", "mac.gamesforone.com/rules/au_patience.html"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Australian%20Patience.shtml")
  ),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
