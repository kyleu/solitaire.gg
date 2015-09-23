package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation initial cards (F0d): -1
 *   Foundation suit match rule (F0s): 5 (Regardless of suit)
 *   Tableau name (T0Nm): Reserve
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 2 (To all tableau piles)
 *   Related games (related): leapyear, acquaintance
 */
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
  description = "An old solitaire game in which no building is allowed on the tableau. The secret to winning is to get extremely lucky before you a" +
    "bandon the game out of shear boredom or to play a more skill-dependent variation like ^sirtommy^ instead.",
  layout = "sf|.t",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any
    )
  ),
  tableaus = Seq(
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
