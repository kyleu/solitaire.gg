// Generated rules for Solitaire.gg.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Auto-move cards to foundation (F0a): 0 (Never)
 *   Foundation add complete sequences only (F0cs): true
 *   Foundation Sets (Fn): 0
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): DDDDDU DDDDDU DDDDDU DDDDDU DDDDU DDDDU DDDDU DDDDU DDDDU DDDDU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Tableau suit match rule for moving stacks (T0ts): 1 (In same suit)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 3 (To all tableau piles if none empty)
 *   Left mouse interface function (leftfunc): 0x2|0x20
 *   Similar to (like): spider
 *   Number of decks (ndecks): 8 (8 decks)
 *   Right mouse interface function (rightfunc): 0
 *   Custom suits (suits): 1 (S)
 *   Victory condition (victory): 3 (All cards on tableau sorted)
 */
object SpiderOneSuit extends GameRules(
  id = "spideronesuit",
  title = "Spider One Suit",
  like = Some("spider"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/spider_one_suit.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/spider_(1_suit).html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Spider.html.en")
  ),
  description = "^spider^ with nothing but spades, spades, spades, as far as the eye can see.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  deckOptions = DeckOptions(
    numDecks = 8,
    suits = Seq(Suit.Spades)
  ),
  stock = Some(
    StockRules(
      dealTo = StockDealTo.TableauIfNoneEmpty,
      maximumDeals = Some(1)
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
