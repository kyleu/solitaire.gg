// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 2 (In different suits)
 *   Tableau suit match rule for moving stacks (T0ts): 2 (In different suits)
 */
object ThumbAndPouch extends GameRules(
  id = "thumbandpouch",
  title = "Thumb and Pouch",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thumb_and_pouch.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/thumb_and_pouch.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/thumb_and_pouch.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thumb-and-pouch.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ThumbandPouch.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/ThumbAndPouch.html"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/ThumbAndPouch.html"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Klondike%20Solitaire.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/thumb_and_pouch.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Thumb_And_Pouch.html.en"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-thumb-and-pouch-13658/")
  ),
  description = "Like ^klondike^, but easier, because cards can be played on tableau cards of any different suit.",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.DifferentSuits,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

