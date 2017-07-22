package models.rules.impl

import models.rules._

object Beetle extends GameRules(
  id = "beetle",
  completed = false,
  title = "Beetle",
  like = Some("spider"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Beetle_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/beetle.htm"),
    Link("Chet Carrie on eHow", "www.ehow.com/how_7215429_play-beetle-solitaire.html")
  ),
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 8, moveCompleteSequencesOnly = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU",
        "UUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
