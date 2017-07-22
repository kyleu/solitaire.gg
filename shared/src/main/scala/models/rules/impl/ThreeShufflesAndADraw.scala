package models.rules.impl

import models.rules._

object ThreeShufflesAndADraw extends GameRules(
  id = "threeshufflesandadraw",
  completed = true,
  title = "Three Shuffles",
  like = Some("labellelucie"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/three_shuffles_and_a_draw.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ThreeShufflesandaDraw.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Three_Shuffles_and_a_Draw_(solitaire)")
  ),
  layout = "::.f|2t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "UUU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      drawsAllowed = 1,
      drawsAfterRedeals = true
    )
  )
)
