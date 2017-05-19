package models.rules.impl

import models.card.Rank
import models.rules._

object Chameleon extends GameRules(
  id = "chameleon",
  completed = false,
  title = "Chameleon",
  like = Some("canfield"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chameleon.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Chameleon.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Canfield_%28solitaire%29")
  ),
  layout = "swf|r|.::t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 3,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 12, cardsFaceDown = -1))
)
