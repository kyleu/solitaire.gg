// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 10
 *   Tableau rank match rule for building (T0r): 0x0080
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Similar to (like): german
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): bavarian
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 *   *vrank (vrank): 0x0080
 *   *vsuit (vsuit): 5
 */
object Bavarian extends GameRules(
  id = "bavarian",
  title = "Bavarian",
  like = Some("german"),
  related = Seq("bavarian"),
  links = Seq(Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/bavarian_patience.htm")),
  description = "Thomas Warfield's easier version of ^german^ patience with a few extra tableau columns.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrapFromKingToAce = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

