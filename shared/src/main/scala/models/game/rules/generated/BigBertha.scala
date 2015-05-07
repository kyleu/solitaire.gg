// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Main Foundation
 *   Maximum cards for foundation (F0m): 12
 *   Foundation name (F1Nm): Kings Foundation
 *   Auto-move cards to foundation (F1a): 1 (Whenever possible)
 *   Foundation low rank (F1b): 13
 *   Number of foundation piles (F1n): 1 (1 stack)
 *   Foundation rank match rule (F1r): 0x0040
 *   Foundation suit match rule (F1s): 5 (Regardless of suit)
 *   Foundation Sets (Fn): 2
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 15
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Waste name (W0Nm): Reserve
 *   Playable waste cards (W0a): true
 *   *W0s (W0s): true
 *   Number of decks (ndecks): 2 (2 decks)
 */
object BigBertha extends GameRules(
  id = "bigbertha",
  title = "Big Bertha",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_bertha.htm"),
    Link("Betsy Gallup on eHow", "www.ehow.com/list_5904884_rules-big-bertha-card-game.html")
  ),
  description = "This two-deck version of ^kingalbert^ which has 14 reserve cards that are all playable, and a separate foundation pile that you ca" +
    "n put all the kings on.",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      name = "Main Foundation",
      numPiles = 8,
      wrapFromKingToAce = true,
      maxCards = 12,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      emptyFilledWith = FillEmptyWith.Aces
    )
  ),
  complete = false
)

