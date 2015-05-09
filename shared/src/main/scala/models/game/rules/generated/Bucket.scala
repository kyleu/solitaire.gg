// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): canister
 *   Number of decks (ndecks): 1 (1 deck)
 *   Related games (related): britishcanister, americancanister, bucket
 */
object Bucket extends GameRules(
  id = "bucket",
  title = "Bucket",
  like = Some("canister"),
  related = Seq("britishcanister", "americancanister", "bucket"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bucket.htm")),
  description = "A ^canister^ variation which ends up looking rather like ^freecell^ without the cells. Naturally, it ends up being rather difficul" +
    "t.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
