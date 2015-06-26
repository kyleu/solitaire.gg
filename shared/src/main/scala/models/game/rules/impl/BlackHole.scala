// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 21 (Deck's low card)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Maximum cards for foundation (F0m): 0
 *   Number of foundation piles (F0n): 1 (1 stack)
 *   Foundation rank match rule (F0r): 160 (Build up or down)
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Initial card restriction (F0u): 7 (Hearts)
 *   Foundation wraps from king to ace (F0w): true
 *   *S0cardsShown (S0cardsShown): 16
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 17
 *   Tableau rank match rule for building (T0r): 0 (May not build)
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 6 (To all foundation piles)
 *   Left mouse interface function (leftfunc): 2
 *   Similar to (like): allinarow
 *   Related games (related): binarystar
 *   Touch interface function (touchfunc): 2
 *   Victory condition (victory): 5 (All cards on foundation or stock)
 */
object BlackHole extends GameRules(
  id = "blackhole",
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
  description = "Like ^allinarow^, this is a variation of ^golf^ without a stock. Invented by David Parlett.",
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
