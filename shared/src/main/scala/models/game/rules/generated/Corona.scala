// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Auto-fill an empty tableau from (T0af): 6 (First waste then stock)
 *   Tableau initial cards (T0d): 3 (3 cards)
 *   Tableau piles (T0n): 12
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): quadrangle
 */
object Corona extends GameRules(
  id = "corona",
  title = "Corona",
  related = Seq("quadrangle"),
  links = Seq(
    Link("Strategy Guide by Dan Fletcher", "www.solitairecentral.com/articles/HowtoPlayCoronaSolitaire.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/corona.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/corona.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/corona.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/corona.php")
  ),
  description = "A game similar to ^fortyandeight^ where spaces are autofilled from the waste and stock. The best way to win seems to be to get luc" +
    "ky.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(3),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.WasteThenStock
    )
  )
)
