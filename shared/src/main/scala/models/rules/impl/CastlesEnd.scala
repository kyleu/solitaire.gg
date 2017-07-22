package models.rules.impl

import models.card.Rank
import models.rules._

object CastlesEnd extends GameRules(
  id = "castlesend",
  completed = true,
  title = "Castle's End",
  like = Some("chessboard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castles_end.htm")),
  layout = ":f::r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(ReserveRules(numPiles = 2, cardsFaceDown = -1))
)
