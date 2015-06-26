// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): midshipman
 */
object Maria extends GameRules(
  id = "maria",
  title = "Maria",
  related = Seq("midshipman"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/maria.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/maria.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/maria.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Maria.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/maria.php"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Maria.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/maria.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/maria.htm")
  ),
  description = "Like ^fortythieves^, but with a 9 by 4 tableau where you build in alternating colors.",
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
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
