package models.rules.impl

import models.rules._

object AuldLangSyne extends GameRules(
  id = "auldlangsyne",
  completed = true,
  title = "Auld Lang Syne",
  related = Seq("leapyear", "acquaintance"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Auld_Lang_Syne_(solitaire)"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/auld_lang_syne.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Auld_Lang_Syne.html.en"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/auld_lang_syne.htm"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/auld_lang_syne.htm")
  ),
  layout = "sf|.t",
  stock = Some(StockRules(dealTo = StockDealTo.Tableau, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 4, suitMatchRule = SuitMatchRule.Any)),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
