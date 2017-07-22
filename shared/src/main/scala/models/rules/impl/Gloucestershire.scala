package models.rules.impl

import models.rules._

object Gloucestershire extends GameRules(
  id = "gloucestershire",
  completed = false,
  title = "Gloucestershire",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gloucestershire.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/gloucestershire.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Gloucestershire.htm")
  ),
  layout = "wf|t",
  deckOptions = DeckOptions(numDecks = 2),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
