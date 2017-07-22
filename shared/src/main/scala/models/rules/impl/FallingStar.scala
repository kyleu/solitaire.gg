package models.rules.impl

import models.card.{Rank, Suit}
import models.pile.set.PileSet
import models.rules._

object FallingStar extends GameRules(
  id = "fallingstar",
  completed = false,
  title = "Falling Star",
  like = Some("signora"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/falling_star.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/falling_star.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/falling_star.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/falling-star.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FallingStar.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/falling_star.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/falling_star.htm")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2, suits = Seq(Suit.Horseshoes, Suit.Stars), lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, initialCards = 1, suitMatchRule = SuitMatchRule.AlternatingColors)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock,
      mayMoveToNonEmptyFrom = PileSet.Behavior.allButReserve,
      mayMoveToEmptyFrom = PileSet.Behavior.allButReserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 11))
)
