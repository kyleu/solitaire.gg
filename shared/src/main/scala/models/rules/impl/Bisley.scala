package models.rules.impl

import models.rules._

object Bisley extends GameRules(
  id = "bisley",
  completed = true,
  title = "Bisley",
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Bisley_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bisley.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/bisley.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bisley.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/bisley.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Bisley.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/bisley.php"),
    Link("John Welford on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-bisley-solitaire-25748/")
  ),
  layout = ":f:::f|tt",
  foundations = Seq(
    FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true),
    FoundationRules(setNumber = 1, numPiles = 4, lowRank = FoundationLowRank.DeckHighRank, rankMatchRule = RankMatchRule.Down, autoMoveCards = true)
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    ),
    TableauRules(
      setNumber = 1,
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
