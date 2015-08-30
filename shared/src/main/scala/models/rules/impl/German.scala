// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation Sets (Fn): 0
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 8
 *   Tableau rank match rule for building (T0r): 128 (Build up)
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau wraps from king to ace (T0w): true
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): bavarian
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 *   *vrank (vrank): 128
 *   *vsuit (vsuit): 5
 */
object German extends GameRules(
  id = "german",
  completed = false,
  title = "German",
  related = Seq("bavarian"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/German_Patience"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/german_patience.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/german_patience.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/german-patience.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/GermanPatience.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/german_patience.php"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/german_patience.html")
  ),
  description = "A strange and difficult game where you must build sequences on the tableau, regardless of suit.",
  layout = "sw|t",
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
      numPiles = 8,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      rankMatchRuleForBuilding = RankMatchRule.Up,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)