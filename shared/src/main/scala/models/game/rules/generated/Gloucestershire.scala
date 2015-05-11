// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Maximum cards for foundation (F0m): 26
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   TODO (F0u): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUU UUUU UUUUU UUUUUU UUUUUUU UUUUUUUU UUUUUUUUU UUUUUUUUUU
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau wraps from king to ace (T0w): true
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Gloucestershire extends GameRules(
  id = "gloucestershire",
  title = "Gloucestershire",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/gloucestershire.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/gloucestershire.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Gloucestershire.htm")
  ),
  description = "A two-deck variation of ^flowergarden^.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      wrapFromKingToAce = true,
      maxCards = 26,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUUU",
        "UUUUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
