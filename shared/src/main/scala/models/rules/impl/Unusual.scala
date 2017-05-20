package models.rules.impl

import models.rules._

object Unusual extends GameRules(
  id = "unusual",
  completed = true,
  title = "Unusual",
  like = Some("cruel"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/unusual.htm")),
  layout = "::f|2t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 24,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
