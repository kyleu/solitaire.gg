package models.rules.impl

import models.card.Suit
import models.rules._

object BlackHole extends GameRules(
  id = "blackhole",
  completed = true,
  title = "Black Hole",
  like = Some("allinarow"),
  related = Seq("binarystar"),
  links = Seq(
    Link("David Parlett's page", "www.davpar.eu/patience/blackhole.html"),
    Link("Solitaire Laboratory", "www.solitairelaboratory.com/golf.html"),
    Link("Shlomi Fish's Blackhole Solver", "www.shlomifish.org/open-source/projects/black-hole-solitaire-solver/"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/black_hole.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/black_hole.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Black_Hole_(solitaire)"),
    Link("Computer Solvability of BlackHole", "ipg.host.cs.st-andrews.ac.uk/papers/AICommsBlackhole-revised.pdf"),
    Link("Jan Wolter's Experiments", "/article/blackhole.html")
  ),
  layout = "f|t",
  victoryCondition = VictoryCondition.AllOnFoundationOrStock,
  foundations = Seq(
    FoundationRules(
      initialCardRestriction = Some(FoundationInitialCardRestriction.SpecificSuit(Suit.Hearts)),
      initialCards = 1,
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.UpOrDown,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 17,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      rankMatchRuleForBuilding = RankMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
