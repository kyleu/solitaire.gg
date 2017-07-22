package models.rules.impl

import models.rules._

object Raglan extends GameRules(
  id = "raglan",
  completed = false,
  title = "Raglan",
  like = Some("kingalbert"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/raglan.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/raglan.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/raglan.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Raglan.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/raglan.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/raglan.htm")
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
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUU",
        "UUU",
        "UU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
