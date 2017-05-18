package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 5 (Regardless of suit)
 *   Number of decks (ndecks): 2 (2 decks)
 */
object LadyPalk extends GameRules(
  id = "ladypalk",
  completed = true,
  title = "Lady Palk",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/lady_palk.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/lady_palk.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/lady_palk.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/beleaguered_castle/lady_palk.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/avenue.htm")
  ),
  layout = "swf|.:t",
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
      numPiles = 8,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
