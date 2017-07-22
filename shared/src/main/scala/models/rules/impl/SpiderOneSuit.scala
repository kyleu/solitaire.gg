package models.rules.impl

import models.card.Suit
import models.rules._

object SpiderOneSuit extends GameRules(
  id = "spideronesuit",
  completed = true,
  title = "Spider One Suit",
  like = Some("spider"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spider_one_suit.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spider_(1_suit).html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider.html.en")
  ),
  layout = "s|t",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(numDecks = 8, suits = Seq(Suit.Spades)),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  tableaus = IndexedSeq(
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
