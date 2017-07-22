package models.rules.impl

import models.rules._

object FortunesFavor extends GameRules(
  id = "fortunesfavor",
  completed = true,
  title = "Fortune's Favor",
  like = Some("busyaces"),
  related = Seq("preference"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Fortune's_Favor"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/fortunes_favor.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/fortunes_favor.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/FortunesFavor.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/fortune_s_favor.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/fortunes_favor.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/fortunes_favor.htm")
  ),
  layout = "sw:f|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
