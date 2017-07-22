package models.rules.impl

import models.card.Rank
import models.rules._

object TheWish extends GameRules(
  id = "thewish",
  completed = true,
  title = "The Wish",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/the_wish.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/TheWish.html")
  ),
  layout = "f|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsOfSameRank,
  deckOptions = DeckOptions(ranks = Seq(Rank.Seven, Rank.Eight, Rank.Nine, Rank.Ten, Rank.Jack, Rank.Queen, Rank.King, Rank.Ace)),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      autoMoveCards = true,
      visible = false
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
