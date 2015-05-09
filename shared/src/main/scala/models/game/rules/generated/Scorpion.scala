// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Stock name (S0Nm): Reserve
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDUUUU DDDUUUU DDDUUUU DDDUUUU UUUUUUU UUUUUUU UUUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Related games (related): chelicera, chinese
 */
object Scorpion extends GameRules(
  id = "scorpion",
  title = "Scorpion",
  related = Seq("chelicera", "chinese"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scorpion.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/scorpion.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Scorpion_(solitaire)"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/scorpion.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Scorpion.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Scorpion.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/scorpion.php"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/scorpion.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/scorpion.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Scorpion.html.en"),
    Link("Lena Games", "www.lenagames.com/bp_files/rul/scorpion.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Scorpion%20Solitaire.shtml"),
    Link("eHow", "www.ehow.com/how_2258258_play-scorpion-solitaire.html")
  ),
  description = "A game with a seven-by-seven tableau, where three cards in the first four piles start face down. Unsorted stacks of cards can be m" +
    "oved around, as in ^yukon^, but cards cannot be moved to the foundation until they form complete sequences, as in ^spider^.",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)
