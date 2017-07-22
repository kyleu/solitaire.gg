package models.rules.impl

import models.rules._

object FlowerGarden extends GameRules(
  id = "flowergarden",
  completed = true,
  title = "Flower Garden",
  related = Seq("wildflower", "brigade"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/flower_garden.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Flower_Garden_(solitaire)"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bouquet.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/FlowerGarden.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/flower_garden.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/FlowerGarden.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/flower_garden.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/flower_garden.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#flower"),
    Link("LenaGames", "www.lenagames.com/bp_files/rul/flower-garden.htm")
  ),
  layout = ":f|t|w",
  waste = Some(
    WasteRules(
      name = "Bouquet",
      cardsShown = 20
    )
  ),
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      name = "Flower Beds",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
