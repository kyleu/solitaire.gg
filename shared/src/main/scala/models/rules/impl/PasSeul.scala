// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Similar to (like): blindalleys
 *   Maximum deals from stock (maxdeals): 1 (1)
 */
object PasSeul extends GameRules(
  id = "passeul",
  completed = false,
  title = "Pas Seul",
  like = Some("blindalleys"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pas_seul.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/pas_seul.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/pas-seul.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/passeul.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Passeul.htm")
  ),
  description = "A ^klondike^ variant with a rectangular tableau, differing from ^blindalleys^ only in the number of passes through the deck allowe" +
    "d. The name refers to a dance sequence for one person.",
  layout = Some("swf|t"),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(3)
    )
  )
)