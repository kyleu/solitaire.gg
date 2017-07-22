package models.rules.impl

import models.rules._

object ThumbAndPouch extends GameRules(
  id = "thumbandpouch",
  completed = true,
  title = "Thumb and Pouch",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/thumb_and_pouch.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/thumb_and_pouch.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/thumb_and_pouch.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/thumb-and-pouch.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/ThumbandPouch.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/ThumbAndPouch.html"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/ThumbAndPouch.html"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Klondike%20Solitaire.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/thumb_and_pouch.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Thumb_And_Pouch.html.en"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-thumb-and-pouch-13658/")
  ),
  layout = "swf|t",
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.DifferentSuits
    )
  )
)
