package models.rules.impl

import models.rules._

object SpiderThreeDeck extends GameRules(
  id = "spiderthreedeck",
  completed = true,
  title = "Spider Three Deck",
  like = Some("bigspider"),
  links = Seq(Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider_Three_Decks.html.en")),
  layout = "sf|.t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 3),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 12,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU",
        "DDDU"
      ),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
