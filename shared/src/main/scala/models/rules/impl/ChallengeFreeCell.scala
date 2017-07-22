package models.rules.impl

import models.rules._

object ChallengeFreeCell extends GameRules(
  id = "challengefreecell",
  completed = false,
  title = "Challenge FreeCell",
  like = Some("freecell"),
  related = Seq("superchallengefreecell"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/challenge_freecell.htm"),
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html#AceDepth")
  ),
  layout = "f|c|t",
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      pilesWithLowCardsAtBottom = 8
    )
  ),
  cells = Some(CellRules())
)
