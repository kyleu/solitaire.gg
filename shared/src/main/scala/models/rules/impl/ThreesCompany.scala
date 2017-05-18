package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): deuces
 *   Low card (lowpip): 3 (3)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): foursup
 *   Enable super moves, whatever those are (supermoves): 0
 */
object ThreesCompany extends GameRules(
  id = "threescompany",
  completed = false,
  title = "Three's Company",
  like = Some("deuces"),
  related = Seq("foursup"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/threes_company.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2,
    lowRank = Rank.Three
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
