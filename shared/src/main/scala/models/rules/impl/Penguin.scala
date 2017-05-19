package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Number of cells (C0n): 7
 *   Foundation initial cards (F0d): 3 (3 cards)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 7 (7 cards)
 *   Piles with low cards at bottom (T0dc): 1 (1 columns)
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Low card (lowpip): -2 (?)
 *   Related games (related): opus
 */
object Penguin extends GameRules(
  id = "penguin",
  completed = false,
  title = "Penguin",
  related = Seq("opus"),
  links = Seq(
    Link("David Parlett's Page.", "www.davpar.eu/patience/penguin.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Penguin_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/penguin.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/penguin.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/freecell/penguin.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/articles/HowToPlayPenguinSolitaire.html")
  ),
  layout = ":.f|c|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 3, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.HighRank,
      pilesWithLowCardsAtBottom = 1
    )
  ),
  cells = Some(CellRules(numPiles = 7))
)
