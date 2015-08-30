// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 8 (8 cards)
 *   Empty tableau is filled with (T0f): 0 (Any card)
 *   Maximum cards per tableau (T0m): 8 (8 cards)
 *   Tableau piles (T0n): 1
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau name (T1Nm): Tableau
 *   Tableau initial cards (T1d): -2 (custom)
 *   Custom initial cards (T1ds): UUUUUUUU UUUUUUUU UUUU UU  UU UUUU UUUUUUUU UUUUUUUU
 *   Empty tableau is filled with (T1f): 0 (Any card)
 *   Tableau piles (T1n): 9
 *   Tableau suit match rule for building (T1s): 4 (In alternating colors)
 *   Tableau sets (Tn): 2 (2 tableau sets)
 *   Number of waste piles (W0n): 0
 *   Related games (related): needle
 */
object Haystack extends GameRules(
  id = "haystack",
  completed = false,
  title = "Haystack",
  related = Seq("needle"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/haystack.htm")),
  description = "A more difficult version of ^needle^ where only 8 cards can be stored in the reserve.",
  layout = Some("f|tt"),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = InitialCards.Count(8),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      maxCards = 8
    ),
    TableauRules(
      setNumber = 1,
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
  )
)