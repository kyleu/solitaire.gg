package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Number of foundation piles (F0n): 4 (4 stacks)
 *   Initial card restriction (F0u): 2 (Unique suits)
 *   Foundation low rank (F1b): 22 (Deck's high card)
 *   Number of foundation piles (F1n): 4 (4 stacks)
 *   Foundation rank match rule (F1r): 32 (Build down)
 *   Initial card restriction (F1u): 2 (Unique suits)
 *   Foundation Sets (Fn): 2
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Maximum cards per tableau (T0m): 2 (2 cards)
 *   Tableau piles (T0n): 20
 *   May move to non-empty tableau from (T0o): 2 (waste)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Maximum deals from stock (maxdeals): 2 (2)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): father
 */
object Grandfather extends GameRules(
  id = "grandfather",
  completed = true,
  title = "Grandfather",
  related = Seq("father"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/grandfather.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/grandfather.htm")
  ),
  description = "A game with twenty tableau piles, each of which can hold any two cards. You build up on half the foundations and down on the other" +
    "s.",
  layout = "::::sw|.f:f|2t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      autoMoveCards = true
    ),
    FoundationRules(
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 20,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      mayMoveToNonEmptyFrom = Seq("waste"),
      maxCards = 2
    )
  )
)
