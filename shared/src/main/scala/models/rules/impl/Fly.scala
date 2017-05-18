package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Reserve name (R0Nm): Fly
 *   Reserve initial cards (R0d): 13
 *   *R0dd (R0dd): 0
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Tableau initial cards (T0d): 0 (None)
 *   Empty tableau is filled from (T0fo): 1 (stock)
 *   Tableau piles (T0n): 5
 *   May move to non-empty tableau from (T0o): 1 (stock)
 *   Tableau rank match rule for building (T0r): 8191 (Regardless of rank)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 7 (Manually)
 *   Similar to (like): frog
 *   Number of decks (ndecks): 2 (2 decks)
 */
object Fly extends GameRules(
  id = "fly",
  completed = true,
  title = "Fly",
  like = Some("frog"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fly.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fly.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fly.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fly.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/fly.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Frog_(game)")
  ),
  layout = "sf|r.:t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Manually,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      initialCards = 8,
      suitMatchRule = SuitMatchRule.Any,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      mayMoveToNonEmptyFrom = Seq("stock"),
      mayMoveToEmptyFrom = Seq("stock")
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Fly",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = 0
    )
  )
)
