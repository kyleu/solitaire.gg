package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): 0 (Nowhere)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 1
 *   Deal cards from stock (dealto): 1 (To all waste piles)
 *   Similar to (like): blockade
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): napoleonsshoulder
 */
object NapoleonsSquare extends GameRules(
  id = "napoleonssquare",
  completed = false,
  title = "Napoleon's Square",
  like = Some("blockade"),
  related = Seq("napoleonsshoulder"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Napoleon's_Square"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/napoleons_square.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/napoleon_s_square.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/napoleon-square.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/napoleons_square.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/NapoleonsSquare.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
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
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
