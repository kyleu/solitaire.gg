package models.rules.impl

import models.rules._

object FortyThieves extends GameRules(
  id = "fortythieves",
  completed = true,
  title = "Forty Thieves",
  related = Seq(
    "robie", "napoleonsquadrilateral", "famousfifty", "fortybandits", "limited", "elba", "threepirates", "squadron",
    "fortythieves3", "sixtythieves", "littlenapoleon", "eightythieves", "mamysusan", "sanjuanhill", "fortythieves4", "thievesrush",
    "josephine"
  ),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Forty_Thieves_(card_game)"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/FortyThieves.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/forty_thieves.html"),
    Link("LenaGames", "www.lenagames.com/bp_files/rul/forty-thieves.htm"),
    Link("Erik Arneson on About.com", "boardgames.about.com/od/solitaire/a/Forty-Thieves.htm"),
    Link("Forty Thieves Solitaire dot Com", "www.fortythievessolitaire.com/fortythievesrules.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/forty_thieves.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Forty_Thieves.html.en"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/fortythieves.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/forty_thieves.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/forty-thieves.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/FortyThieves.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/forty_thieves.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/forty_thieves.php")
  ),
  layout = "swf|.t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 8,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
