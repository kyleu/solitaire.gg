// Generated rules for Solitaire.gg.
package models.game.rules.impl

import models.game.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau name (T0Nm): Flower Beds
 *   Tableau initial cards (T0d): 6 (6 cards)
 *   Tableau piles (T0n): 6
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Waste name (W0Nm): Bouquet
 *   Playable waste cards (W0a): true
 *   Number of cards shown (W0cardsShown): 20
 *   *W0s (W0s): true
 *   Related games (related): wildflower, brigade
 */
object FlowerGarden extends GameRules(
  id = "flowergarden",
  completed = true,
  title = "Flower Garden",
  related = Seq("wildflower", "brigade"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/flower_garden.htm"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Flower_Garden_(solitaire)"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/bouquet.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/FlowerGarden.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/flower_garden.htm"),
    Link("dogMelon", "www.dogmelon.com.au/solhelp/FlowerGarden.shtml"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/flower_garden.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/flower_garden.htm"),
    Link("Lady Cadogan's Illustrated Games of Solitaire or Patience", "www.gutenberg.org/files/21642/21642-h/21642-h.htm#flower"),
    Link("LenaGames", "www.lenagames.com/bp_files/rul/flower-garden.htm")
  ),
  description = "The six stacks of six cards in the tableau are called \"flower beds\". You can build down on them in any suit.  Instead of stock a" +
    "nd waste piles, you have a bouquet of 16 cards, any of which can be played at any time.",
  layout = Some(":f|t|w"),
  waste = Some(
    WasteRules(
      name = "Bouquet",
      cardsShown = 20
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Flower Beds",
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
