package models.rules.impl

import models.rules._

object Indefatigable extends GameRules(
  id = "indefatigable",
  completed = true,
  title = "Indefatigable",
  like = Some("cruel"),
  related = Seq("royalfamily"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/indefatigable.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Indefatigable.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/the_indefatigable.php"),
    Link("Jan Wolter's Experiments", "/article/indefatigable.html")
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
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2
    )
  )
)
