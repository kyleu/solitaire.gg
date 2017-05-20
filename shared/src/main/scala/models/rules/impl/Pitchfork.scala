package models.rules.impl

import models.card.Rank
import models.rules._

object Pitchfork extends GameRules(
  id = "pitchfork",
  completed = false,
  title = "Pitchfork",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pitchfork.htm")),
  layout = "f|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUU",
        "UU",
        "",
        "UU",
        "UUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(ReserveRules(initialCards = 7))
)
