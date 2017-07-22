package models.rules.impl

import models.card.Rank
import models.rules._

object Rainbow extends GameRules(
  id = "rainbow",
  completed = true,
  title = "Rainbow",
  like = Some("canfield"),
  related = Seq("kansas"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rainbow.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/rainbow.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/rainbow.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Rainbow.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Rainbow.html"),
    Link("PySol", "pysolfc.sourceforge.net/doc/rules/rainbow.html"),
    Link("Swoop Solitaire", "www.swoopsoftware.com/solitaire_rules/rainbow.html")
  ),
  layout = "swf|r::t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
