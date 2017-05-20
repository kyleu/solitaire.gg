package models.rules.impl

import models.card.Suit
import models.rules._

object SpiderTwoSuits extends GameRules(
  id = "spidertwosuits",
  completed = true,
  title = "Spider Two Suits",
  like = Some("spider"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spider_two_suits.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spider_(2_suits).html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider.html.en")
  ),
  layout = "sf|.:::t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 4, suits = Seq(Suit.Hearts, Suit.Spades)),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 16,
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
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
