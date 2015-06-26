// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): britishcanister, americancanister, bucket
 */
object Canister extends GameRules(
  id = "canister",
  title = "Canister",
  related = Seq("britishcanister", "americancanister", "bucket"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canister.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/canister.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/canister.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Canister.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/canister.htm")
  ),
  description = "Yet another generic solitaire game, with all cards dealt face up and no stock. This is quite easy when it's not impossible.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
