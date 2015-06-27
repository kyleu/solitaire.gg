// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): stages, courtyard, dimes, fortunesfavor, deuces
 *   Enable super moves, whatever those are (supermoves): 1
 */
object BusyAces extends GameRules(
  id = "busyaces",
  title = "Busy Aces",
  related = Seq("stages", "courtyard", "dimes", "fortunesfavor", "deuces"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/busy_aces.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/busy_aces.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/busy_aces.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/busy_aces.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BusyAces.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/busy-aces.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/busy_aces.htm"),
    Link("Amelia Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/view-article/1866274/")
  ),
  description = "A fairly easy game dating back to 1939. Twelve tableau stacks of one card each mean you can easily get lots of empty spaces to wor" +
    "k with.",
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
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
