package models.rules.impl

import models.rules._

object Cleopatra extends GameRules(
  id = "cleopatra",
  completed = false,
  title = "Cleopatra",
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cleopatra.htm")),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "DU",
        "UDU",
        "DUDU",
        "UDUDU",
        "DUDUDU",
        "UDUDUDU",
        "DUDUDU",
        "UDUDU",
        "DUDU",
        "UDU",
        "DU",
        "U"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
