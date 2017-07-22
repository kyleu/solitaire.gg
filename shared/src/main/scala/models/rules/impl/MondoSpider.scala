package models.rules.impl

import models.card.Suit
import models.rules._

object MondoSpider extends GameRules(
  id = "mondospider",
  completed = false,
  title = "Mondo Spider",
  like = Some("spider"),
  layout = ".::::sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2, suits = Suit.all),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 18,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU",
        "UUUUUUU",
        "DDDDDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
