// Generated rules for Solitaire.gg.
package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Reserve initial cards (R0d): 7
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): UUUUUUUU UUUUUUUU UUUU UU  UU UUUU UUUUUUUU UUUUUUUU
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Low card (lowpip): -2 (?)
 *   Enable super moves, whatever those are (supermoves): 1
 */
object Pitchfork extends GameRules(
  id = "pitchfork",
  title = "Pitchfork",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/pitchfork.htm")),
  description = "Thomas Warfield's variation of ^needle^ and ^haystack^ in which you cannot build on the reserve.",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUU",
        "UU",
        "",
        "UU",
        "UUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 7,
      cardsFaceDown = 0
    )
  )
)
