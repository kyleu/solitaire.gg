package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 0 (May not build)
 *   Deal cards from stock (dealchunk): 1 (One by one)
 *   Similar to (like): canfield
 *   Low card (lowpip): 1 (A)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Related games (related): coyote
 */
object Acme extends GameRules(
  id = "acme",
  completed = true,
  title = "Acme",
  like = Some("canfield"),
  related = Seq("coyote"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/acme.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/acme.htm"),
    Link("Jan Wolter's Experiments", "/article/acme.html")
  ),
  description = "A difficult variation of ^canfield^ where you build in suit, can't move sequences, and only get two passes through the stock. \"Ac" +
    "me,\" the greek word for the zenith, was a popular name for companies who wanted to be listed first in the phone book until the ^c" +
    "oyote^ and Roadrunner ruined it.",
  layout = "swf|r::t",
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
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
