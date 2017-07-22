package models.rules.impl

import models.rules._

object Stonewall extends GameRules(
  id = "stonewall",
  completed = false,
  title = "Stonewall",
  related = Seq("trevigarden"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Stonewall_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/stonewall.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/stonewall.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Stonewall.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/stonewall.php")
  ),
  layout = "wf|t",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.OddNumbered
    )
  )
)
