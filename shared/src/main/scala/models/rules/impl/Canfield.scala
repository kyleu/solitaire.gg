package models.rules.impl

import models.card.Rank
import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 1 (1 cards)
 *   Reserve initial cards (R0d): 13
 *   Number of reserve piles (R0n): 1
 *   Auto-fill an empty tableau from (T0af): 1
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Tableau piles (T0n): 4
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Tableau suit match rule for moving stacks (T0ts): 4 (In alternating colors)
 *   Deal cards from stock (dealchunk): 3 (Three at a time)
 *   Low card (lowpip): -2 (?)
 *   Maximum deals from stock (maxdeals): 0
 *   Related games (related): rainbow, storehouse, acme, canfieldgallery, superiorcanfield, canfieldrush, demo...
 */
object Canfield extends GameRules(
  id = "canfield",
  completed = true,
  title = "Canfield",
  related = Seq("rainbow", "storehouse", "acme", "canfieldgallery", "superiorcanfield", "canfieldrush", "demonsandthieves", "chameleon"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Canfield_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/canfield.htm"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Canfield.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/canfield.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Canfield.html.en"),
    Link("Solitaire City", "www.solitairecity.com/Help/Demon.shtml"),
    Link("Jan Wolter's Experiments", "/article/canfield.html")
  ),
  description = "An old Casino game where the house usually wins. It's distinctive features include a reserve and foundations built up from the val" +
    "ue of one random card dealt into them.",
  layout = "swf|:r:t",
  deckOptions = DeckOptions(
    lowRank = Rank.Unknown
  ),
  stock = Some(
    StockRules(
      cardsDealt = StockCardsDealt.Count(3)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 1,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Reserve
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 13,
      cardsFaceDown = -1
    )
  )
)
