package models.rules.impl

import models.rules._

object Maria extends GameRules(
  id = "maria",
  completed = true,
  title = "Maria",
  related = Seq("midshipman"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/maria.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/maria.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/maria.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Maria.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/maria.php"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Maria.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/maria.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/maria.htm")
  ),
  layout = "swf|:t",
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
      numPiles = 9,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
