package models.rules.impl

import models.card.Rank
import models.rules._

object Storehouse extends GameRules(
  id = "storehouse",
  completed = false,
  title = "Storehouse",
  like = Some("canfield"),
  related = Seq("doublestorehouse"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/storehouse.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/storehouse.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/storehouse.htm"),
    Link("Swoop Solitaire", "www.swoopsoftware.com/solitaire_rules/storehouse.html"),
    Link("An 1898 description", "howtoplaysolitaire.blogspot.com/2010/06/storehouse-single-deck-solitaire-game.html"),
    Link("Jan Wolter's Experiments", "/article/storehouse.html")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Two),
  stock = Some(StockRules(maximumDeals = Some(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(name = "Storehouse", initialCards = 13, cardsFaceDown = -1))
)
