// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 5
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Low card (lowpip): -2 (?)
 *   Related games (related): czarina
 *   Enable super moves, whatever those are (supermoves): 1
 */
object FourSeasons extends GameRules(
  id = "fourseasons",
  completed = false,
  title = "Four Seasons",
  related = Seq("czarina"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Four_Seasons_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/four_seasons.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/four_seasons.htm"),
    Link("wikiHow", "www.wikihow.com/Play-Four-Seasons-Solitaire"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/four_seasons.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Royal_East.html.en")
  ),
  description = "A simple game of luck and skill where you move cards one at a time, stacking regardless of suit. The five tableau piles are suppos" +
    "ed to be arranged in a cross with the foundation piles in the four corners, but Politaire is still too stupid to do that.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)