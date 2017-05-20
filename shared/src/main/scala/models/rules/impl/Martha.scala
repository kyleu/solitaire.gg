package models.rules.impl

import models.rules._

object Martha extends GameRules(
  id = "martha",
  completed = true,
  title = "Martha",
  related = Seq("stewart"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/martha.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Martha_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/martha.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/martha.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/martha.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Martha.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Martha%20Solitaire.shtml")
  ),
  layout = "::::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
