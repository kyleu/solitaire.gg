// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Number of decks (ndecks): 2 (2 decks)
 *   Related games (related): fredsspider, chinesespider, astrocyte, mondospider, tarantula, trillium, spidero...
 *   Right mouse interface function (rightfunc): 0
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object Spider extends GameRules(
  id = "spider",
  title = "Spider",
  related = Seq(
    "fredsspider", "chinesespider", "astrocyte", "mondospider", "tarantula", "trillium", "spideronesuit", "spidertwosuits",
    "spiderette", "blackwidow", "hugespider", "beetle", "bigspider"
  ),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Spider_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spider.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spider.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/spider.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider.html.en"),
    Link("Solitaire City", "www.solitairecity.com/Help/Spider.shtml"),
    Link("wikiHow", "www.wikihow.com/Play-Spider-Solitaire"),
    Link("Microsoft", "windows.microsoft.com/en-us/windows/spider-solitaire-how-to"),
    Link("Hagai Eisenberg", "ezinearticles.com/?Spider-Solitaire---History-and-Rules&id=6806794"),
    Link("Dan Fletcher's Strategy Guide", "www.solitairecentral.com/articles/SpiderSolitaireAStrategyGuideForBeginners.html"),
    Link("Boris Sandberg's Strategy Guide", "www.solitairecentral.com/articles/SpiderSolitaireAWinningStrategy.html"),
    Link("Steve Weiss", "home.comcast.net/~srweiss/spider/"),
    Link("Alex Robinson's Spider Solver", "www.tranzoa.net/~alex/plspider.htm")
  ),
  description = "On the 10 tableau piles you can build down regardless of suit, but you can only move single suit sequences. When you click on the " +
    "stock, one card will be dealt to each tableau pile. Single cards cannot be moved to the foundation, only complete sequences.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 8,
      wrapFromKingToAce = true,
      moveCompleteSequencesOnly = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "DDDDDU",
        "DDDDDU",
        "DDDDDU",
        "DDDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU",
        "DDDDU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.Aces
    )
  )
)
