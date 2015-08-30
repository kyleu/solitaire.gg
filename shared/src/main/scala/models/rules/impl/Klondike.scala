// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Tableau initial cards (T0d): -1 (1 to n cards)
 *   Tableau cards face down (T0df): 100
 *   Empty tableau is filled with (T0f): 1 (Kings only)
 *   Tableau piles (T0n): 7
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Maximum deals from stock (maxdeals): 0
 *   Related games (related): whitehorse, kingsley, trigon, goldmine, thoughtful, klondikegallery, chineseklon...
 */
object Klondike extends GameRules(
  id = "klondike",
  completed = true,
  title = "Klondike",
  related = Seq(
    "whitehorse", "kingsley", "trigon", "goldmine", "thoughtful", "klondikegallery", "chineseklondike", "athena",
    "saratoga", "endlessharp", "smokey", "spike", "gilbert", "jumboklondike", "chinaman", "klondike1card",
    "sevendevils"
  ),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Klondike_(solitaire)"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Klondike.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/klondike.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/Klondike%20Solitaire.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/klondike.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/klondike_by_threes.html"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#canfield"),
    Link("An 1897 description", "howtoplaysolitaire.blogspot.com/2010/06/canfield-or-klondike-single-deck.html"),
    Link("Robert Abbott's Strategy Guide", "www.logicmazes.com/sol/"),
    Link("Boris Sandberg's Strategy Tips", "www.solitairecentral.com/articles/KlondikeSolitaireWinningStrategyTips.html"),
    Link("A Strategy for Winning Klondike Solitaire", "www.jupiterscientific.org/sciinfo/AStrategryForWinningKlondikeSolitaire.html"),
    Link("Usman Latif's analysis of the number of Klondike cards in which no cards can be played.", "www.techuser.net/klondikeprob.html"),
    Link("Bill's Solitaire Tester", "www.roziturnbull.com/bill/Solitaire/solitaire.htm")
  ),
  description = "The world's most famous solitaire game features a triangular tableau where you build down in alternating colors.",
  layout = "swf|t",
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = FillEmptyWith.HighRank
    )
  )
)