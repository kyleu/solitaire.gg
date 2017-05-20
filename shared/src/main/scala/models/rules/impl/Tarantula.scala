package models.rules.impl

import models.rules._

object Tarantula extends GameRules(
  id = "tarantula",
  completed = true,
  title = "Tarantula",
  like = Some("spider"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/tarantula.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/tarantula.php")
  ),
  layout = "s:f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDU",
        "DDDDDU",
        "DDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameColor
    )
  )
)
