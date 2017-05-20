package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

object Lasker extends GameRules(
  id = "lasker",
  completed = true,
  title = "Lasker",
  like = Some("chessboard"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lasker.htm")),
  layout = ":::f|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      rankMatchRuleForMovingStacks = RankMatchRule.UpOrDown
    )
  )
)
