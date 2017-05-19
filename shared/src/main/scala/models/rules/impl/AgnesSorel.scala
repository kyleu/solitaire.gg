package models.rules.impl

import models.card.Rank
import models.rules._

object AgnesSorel extends GameRules(
  id = "agnessorel",
  completed = true,
  title = "Agnes Sorel",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/agnes_sorel.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/AgnesSorel.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/agnes_sorel.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Agnes.html.en"),
    Link("Michael Smoker on HobbyHub", "www.hobbyhub360.com/index.php/view-article/1937518/"),
    Link("Jan Wolter's Experiments", "/article/agnessorel.html")
  ),
  layout = "s.f|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameColor,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameColor
    )
  )
)
