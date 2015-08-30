// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Tableau cards face down (T0df): 101
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): tripleinterchange, breakwater, unlimited
 */
object Interchange extends GameRules(
  id = "interchange",
  completed = false,
  title = "Interchange",
  related = Seq("tripleinterchange", "breakwater", "unlimited"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/interchange.htm"),
    Link("The Dao of Interchange", "www.goodsol.com/games/interchange-dao.html"),
    Link("Michael Smoker's very confusing description", "www.hobbyhub360.com/index.php/interchange-solitaire-card-game-8745/")
  ),
  description = "An extremely difficult ^fortythieves^ variant with alternate cards dealt face down.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.EvenNumbered,
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)