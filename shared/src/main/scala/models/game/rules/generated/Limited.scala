// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): doublelimited
 */
object Limited extends GameRules(
  id = "limited",
  title = "Limited",
  like = Some("fortythieves"),
  related = Seq("doublelimited"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/limited.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Forty_Thieves_(card_game)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/limited.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/limited.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Limited.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/limited.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/limited.htm"),
    Link("Battleline Games", "www.limitedsolitaire.com/LimitedSolitaireInstructions.html"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Limited.html")
  ),
  description = "Like ^fortythieves^, but with a 12 by 3 tableau.",
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
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
