package models.rules.impl

import models.rules._

object VariegatedCanfield extends GameRules(
  id = "variegatedcanfield",
  completed = false,
  title = "Variegated Canfield",
  like = Some("doublecanfield"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/variegated_canfield.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/variegated_canfield.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/variegated_canfield.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/variegated-canfield.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/VariegatedCanfield.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/canfield/variegated_canfield.htm")
  ),
  layout = "swf|r|t",
  deckOptions = DeckOptions(numDecks = 2),
  stock = Some(StockRules(maximumDeals = Some(3), cardsDealt = StockCardsDealt.Count(3))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 8, initialCards = 8, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 5,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(ReserveRules(initialCards = 13, cardsFaceDown = -1))
)
