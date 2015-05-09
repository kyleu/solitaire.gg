// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau cards face down (T0df): 100
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Similar to (like): numberten
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): emperor
 */
object RankAndFile extends GameRules(
  id = "rankandfile",
  title = "Rank and File",
  like = Some("numberten"),
  related = Seq("emperor"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/rank_and_file.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/RankAndFile.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/RankandFile.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/rank_and_file.php"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/forty_thieves/rank_and_file.htm")
  ),
  description = "Like ^numberten^, but three cards in each stack are dealt face down.",
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
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
