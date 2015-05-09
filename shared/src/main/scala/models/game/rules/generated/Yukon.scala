// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U DUUUUU DDUUUUU DDDUUUUU DDDDUUUUU DDDDDUUUUU DDDDDDUUUUU
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau rank match rule for moving stacks (T0tr): 8191 (Regardless of rank)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Related games (related): yukoncells, brisbane, yukononesuit, yukonicplague, alaska, yukonkings, yakutatba...
 */
object Yukon extends GameRules(
  id = "yukon",
  title = "Yukon",
  related = Seq("yukoncells", "brisbane", "yukononesuit", "yukonicplague", "alaska", "yukonkings", "yakutatbay"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Yukon_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/yukon.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/yukon.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/yukon.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Yukon.html.en"),
    Link("About Dot Com", "boardgames.about.com/od/solitaire/a/yukon.htm"),
    Link("Dan Fletcher's Strategy Guide", "www.solitairecentral.com/articles/YukonSolitaireStrategyGuide.html")
  ),
  description = "A well-known game with no stock, in which stacks of cards can be moved even if they aren't in sequence.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DUUUUU",
        "DDUUUUU",
        "DDDUUUUU",
        "DDDDUUUUU",
        "DDDDDUUUUU",
        "DDDDDDUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.Kings
    )
  )
)
