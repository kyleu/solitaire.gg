package models.rules.impl

import models.rules._

object BoxKite extends GameRules(
  id = "boxkite",
  completed = true,
  title = "Box Kite",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Box_Kite_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/box_kite.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/box_kite.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/box_kite.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/box-kite.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BoxKite.htm")
  ),
  layout = ":.f:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(8),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
