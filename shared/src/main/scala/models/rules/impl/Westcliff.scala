package models.rules.impl

import models.rules._

object Westcliff extends GameRules(
  id = "westcliff",
  completed = true,
  title = "Westcliff",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/westcliff.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Westcliff_(solitaire)"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/westcliff.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/westcliff.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Westcliff.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Westcliff.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/westcliff.php"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Westcliff.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/westcliff.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/westcliff.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Westhaven.html.en"),
    Link("John Welford on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-westcliff-solitaire-28948/")
  ),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(numPiles = 10, initialCards = InitialCards.Count(3)))
)
