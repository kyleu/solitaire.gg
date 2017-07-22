package models.rules.impl

import models.rules._

object FreeCell extends GameRules(
  id = "freecell",
  completed = true,
  title = "FreeCell",
  related = Seq(
    "sixbyfour", "ephemeralfreecell", "challengefreecell", "antares", "sevenbyfive", "spidercells", "bigfreecell", "chinesefreecell",
    "sevenbyfour", "invertedfreecell", "selectivefreecell", "doublefreecell", "freecellduplex"
  ),
  links = Seq(
    Link("Michael Keller's amazing FreeCell FAQ", "solitairelaboratory.com/fcfaq.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/FreeCell"),
    Link("FreeCell Solitaire", "www.solitairecentral.com/articles/FreeCellSolitaireAWinningStrategy.html")
  ),
  layout = "c:f|.t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  cells = Some(CellRules())
)
