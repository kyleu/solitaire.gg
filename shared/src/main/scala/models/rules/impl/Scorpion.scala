package models.rules.impl

import models.rules._

object Scorpion extends GameRules(
  id = "scorpion",
  completed = true,
  title = "Scorpion",
  related = Seq("chelicera", "chinese"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/scorpion.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/scorpion.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Scorpion_(solitaire)"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/scorpion.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Scorpion.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Scorpion.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/scorpion.php"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/scorpion.htm"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/scorpion.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Scorpion.html.en"),
    Link("Lena Games", "www.lenagames.com/bp_files/rul/scorpion.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Scorpion%20Solitaire.shtml"),
    Link("eHow", "www.ehow.com/how_2258258_play-scorpion-solitaire.html")
  ),
  layout = "s.f|t",
  stock = Some(
    StockRules(
      name = "Reserve",
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "DDDUUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)
