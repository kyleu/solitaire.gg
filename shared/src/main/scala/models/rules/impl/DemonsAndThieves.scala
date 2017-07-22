package models.rules.impl

import models.card.Rank
import models.rules._

object DemonsAndThieves extends GameRules(
  id = "demonsandthieves",
  completed = true,
  title = "Demons and Thieves",
  like = Some("canfield"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/demons_and_thieves.htm")),
  layout = "swf|r:.tt",
  deckOptions = DeckOptions(numDecks = 2, lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Left Tableau",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    ),
    TableauRules(
      name = "Right Tableau",
      setNumber = 1,
      numPiles = 5,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
