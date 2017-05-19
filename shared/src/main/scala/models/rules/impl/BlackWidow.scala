package models.rules.impl

import models.rules._

object BlackWidow extends GameRules(
  id = "blackwidow",
  completed = true,
  title = "Black Widow",
  like = Some("spider"),
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/BlackWidow.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/black-widow.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BlackWidow.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/black_widow.php"),
    Link("Bryan Cohen on eHow", "www.ehow.com/list_6702718_black-widow-card-game-rules.html")
  ),
  layout = "s.f|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 8, moveCompleteSequencesOnly = true)),
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
      suitMatchRuleForMovingStacks = SuitMatchRule.Any
    )
  )
)
