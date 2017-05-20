package models.rules.impl

import models.card.Rank
import models.rules._

object Signora extends GameRules(
  id = "signora",
  completed = false,
  title = "Signora",
  like = Some("colonel"),
  related = Seq("doublesignora", "fallingstar", "blondesandbrunettes", "roman"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/signora.htm")),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, suitMatchRule = SuitMatchRule.AlternatingColors)),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau"),
      mayMoveToEmptyFrom = Seq("stock", "pyramid", "waste", "pocket", "cell", "foundation", "tableau")
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
