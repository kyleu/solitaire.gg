package models.rules.impl

import models.card.Rank
import models.game._
import models.rules._

object FourSeasons extends GameRules(
  id = "fourseasons",
  completed = true,
  title = "Four Seasons",
  related = Seq("czarina"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Four_Seasons_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/four_seasons.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/four_seasons.htm"),
    Link("wikiHow", "www.wikihow.com/Play-Four-Seasons-Solitaire"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/four_seasons.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Royal_East.html.en")
  ),
  layout = "swf|:t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
