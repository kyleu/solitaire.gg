// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 0
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 3 (In same color)
 *   Tableau suit match rule for moving stacks (T0ts): 3 (In same color)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Low card (lowpip): -2 (?)
 */
object AgnesSorel extends GameRules(
  id = "agnessorel",
  completed = true,
  title = "Agnes Sorel",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/agnes_sorel.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/AgnesSorel.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/agnes_sorel.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Agnes.html.en"),
    Link("Michael Smoker on HobbyHub", "www.hobbyhub360.com/index.php/view-article/1937518/"),
    Link("Jan Wolter's Experiments", "/article/agnessorel.html")
  ),
  description = "A variation on ^klondike^ where cards are dealt directly onto the tableau as in ^spider^.",
  layout = "s.f|t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameColor,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameColor
    )
  )
)
