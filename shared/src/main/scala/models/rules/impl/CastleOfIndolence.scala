package models.rules.impl

import models.rules._

object CastleOfIndolence extends GameRules(
  id = "castleofindolence",
  completed = true,
  title = "Castle of Indolence",
  like = Some("beleagueredcastle"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/castle_of_indolence.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/castle_of_indolence.htm")
  ),
  layout = "f|::r|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(FoundationRules(numPiles = 8, suitMatchRule = SuitMatchRule.Any, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    numPiles = 8,
    initialCards = InitialCards.RestOfDeck,
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.None
  )),
  reserves = Some(ReserveRules(numPiles = 4, initialCards = 13, cardsFaceDown = -1))
)
