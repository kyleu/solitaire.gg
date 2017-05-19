package models.rules.impl

import models.card.Suit
import models.rules._

object WaningMoon extends GameRules(
  id = "waningmoon",
  completed = false,
  title = "Waning Moon",
  related = Seq("thirtyninesteps", "eclipse", "lucasleaps"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/waning_moon.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/waning_moon.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/WaningMoon.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/waning_moon.htm")
  ),
  layout = "swf|t",
  deckOptions = DeckOptions(numDecks = 2, suits = Seq(Suit.Hearts, Suit.Spades, Suit.Clubs, Suit.Horseshoes)),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 13,
    initialCards = InitialCards.Count(4),
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    suitMatchRuleForMovingStacks = SuitMatchRule.None
  ))
)
