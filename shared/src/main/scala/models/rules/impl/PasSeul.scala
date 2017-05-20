package models.rules.impl

import models.rules._

object PasSeul extends GameRules(
  id = "passeul",
  completed = true,
  title = "Pas Seul",
  like = Some("blindalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pas_seul.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/pas_seul.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/pas-seul.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/passeul.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Passeul.htm")
  ),
  layout = "swf|.t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(3)
    )
  )
)
