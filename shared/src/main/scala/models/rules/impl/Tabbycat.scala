package models.rules.impl

import models.rules._

object Tabbycat extends GameRules(
  id = "tabbycat",
  completed = false,
  title = "Tabbycat",
  like = Some("manx"),
  links = Seq(
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/TabbyCat.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/tabby_cat.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Tabby_Cat_(solitaire)"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/tabbycat.html")
  ),
  layout = "sf|tt",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauFirstSet,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      suitMatchRule = SuitMatchRule.Any,
      moveCompleteSequencesOnly = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    ),
    TableauRules(
      name = "Tail",
      setNumber = 1,
      numPiles = 1,
      initialCards = InitialCards.Count(0),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any
    )
  )
)
