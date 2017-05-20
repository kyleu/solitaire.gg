package models.rules.impl

import models.rules._

object Lucas extends GameRules(
  id = "lucas",
  completed = true,
  title = "Lucas",
  like = Some("thirtyninesteps"),
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/lucas.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Forty_Thieves_(card_game)"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Lucas.shtml"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/lucas.php"),
    Link("Erik Arneson on About.com", "boardgames.about.com/od/solitaire/a/Forty-Thieves.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Lucas.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/lucas.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/lucas.htm")
  ),
  layout = "sw:.f|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
