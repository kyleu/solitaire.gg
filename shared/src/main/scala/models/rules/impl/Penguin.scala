package models.rules.impl

import models.card.Rank
import models.rules._

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
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 3, autoMoveCards = true)),
  tableaus = IndexedSeq(
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
