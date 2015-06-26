// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 13
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): thirtyninesteps
 *   Number of decks (ndecks): 1 (1 deck)
 *   Custom suits (suits): 0
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Lucas extends GameRules(
  id = "lucas",
  title = "Lucas",
  like = Some("thirtyninesteps"),
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lucas.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Forty_Thieves_(card_game)"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Lucas.shtml"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/lucas.php"),
    Link("Erik Arneson on About.com", "boardgames.about.com/od/solitaire/a/Forty-Thieves.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Lucas.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lucas.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/lucas.htm")
  ),
  description = "A ^fortythieves^ variant with thirteen tableau piles and aces starting on the foundation.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
