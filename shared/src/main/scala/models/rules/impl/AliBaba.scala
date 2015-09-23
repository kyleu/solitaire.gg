package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Related games (related): cassim
 */
object AliBaba extends GameRules(
  id = "alibaba",
  completed = true,
  title = "Ali Baba",
  related = Seq("cassim"),
  links = Seq(
    Link("Solitaire Central", "www.solitairecentral.com/rules/AliBaba.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/ali_baba.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/ali-baba.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/ali_baba.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/ali_baba.htm"),
    Link("Jan Wolter's Experiments", "/article/alibaba.html")
  ),
  description = "A one-deck variation of ^fortyandeight^ where you can move sequences of cards together instead of just one at a time. With 40 card" +
    "s in the tableau, you only have 12 cards in your deck which makes for a lot of unsolvable games. But with a bit of luck you can op" +
    "en an empty space in your tableau and then things are likely to go smoothly.",
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
    )
  )
)
