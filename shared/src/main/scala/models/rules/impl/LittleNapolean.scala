package models.rules.impl

import models.rules._

object LittleNapolean extends GameRules(
  id = "littlenapoleon",
  completed = false,
  title = "Little Napolean",
  like = Some("fortythieves"),
  related = Seq("mcclellan"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/little_napoleon.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/LittleNapoleon.htm"),
    Link("Super Solitaire", "supersolitaire.weisswo.com/Games+Rules/Entries/2009/2/15_Little_Napoleon_Solitaire.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/little_napoleon.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
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
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
