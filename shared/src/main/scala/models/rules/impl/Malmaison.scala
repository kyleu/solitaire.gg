package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Similar to (like): sixtythieves
 *   Number of decks (ndecks): 3 (3 decks)
 *   Related games (related): rueil
 */
object Malmaison extends GameRules(
  id = "malmaison",
  completed = false,
  title = "Malmaison",
  like = Some("sixtythieves"),
  related = Seq("rueil"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/malmaison.htm")),
  description = "A difficult four-deck version of ^josephine^, or, if you prefer, a version of ^eightythieves^ which allows sequences to be moved. " +
    "Invented by Thomas Warfield.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 3
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
