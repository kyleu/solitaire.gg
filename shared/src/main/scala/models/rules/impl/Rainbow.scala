// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): canfield
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 1 (1)
 *   Related games (related): kansas
 */
object Rainbow extends GameRules(
  id = "rainbow",
  completed = false,
  title = "Rainbow",
  like = Some("canfield"),
  related = Seq("kansas"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rainbow.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/rainbow.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/rainbow.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Rainbow.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Rainbow.html"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/rainbow.html"),
    Link("Swoop Solitaire", "www.swoopsoftware.com/solitaire_rules/rainbow.html")
  ),
  description = "A variation of ^canfield^ in which you can build regardless of suit.",
  layout = Some("swf|r|t"),
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
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)