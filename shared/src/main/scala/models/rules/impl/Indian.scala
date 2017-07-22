package models.rules.impl

import models.rules._

object Indian extends GameRules(
  id = "indian",
  completed = false,
  title = "Indian",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/indian.htm"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/indian.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Indian.html"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/indian.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Indian.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Indian.html"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/indian.php"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/indian.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/indian.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/indian.htm")
  ),
  layout = "swf|t",
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
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(1),
      suitMatchRuleForBuilding = SuitMatchRule.DifferentSuits,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
