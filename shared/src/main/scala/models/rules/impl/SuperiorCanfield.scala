package models.rules.impl

import models.card.Rank
import models.rules._

object SuperiorCanfield extends GameRules(
  id = "superiorcanfield",
  completed = false,
  title = "Superior Canfield",
  like = Some("canfield"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/superior_canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SuperiorCanfield.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/superior_canfield.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/superior-canfield.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/superior_canfield.php")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  stock = Some(StockRules(cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, initialCards = 1, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13))
)
