package models.rules.impl

import models.rules._

object Alaska extends GameRules(
  id = "alaska",
  completed = false,
  title = "Alaska",
  like = Some("yukon"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/alaska.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Alaska.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/alaska.html"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-alaska-13672/")
  ),
  layout = "f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
