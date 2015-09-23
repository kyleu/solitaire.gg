package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation low rank (F0b): 20 (Any Card)
 *   Maximum cards for foundation (F0m): 0
 *   Auto-fill an empty tableau from (T0af): 4 (Stock)
 *   Tableau initial cards (T0d): 1 (1 card)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau piles (T0n): 9
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Deal cards from stock (dealto): 8 (Never)
 *   Left mouse interface function (leftfunc): 1
 *   Similar to (like): simplepairs
 *   Card removal method (pairs): 5
 *   Victory condition (victory): 1 (All but 4 cards per deck on foundation)
 */
object BlockTen extends GameRules(
  id = "blockten",
  completed = false,
  title = "Block Ten",
  like = Some("simplepairs"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/block_ten.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/block-ten.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/BlockTen.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Block_Ten.html.en"),
    Link("Erik Arnesson on About.com", "boardgames.about.com/od/solitaire/a/block_ten.htm"),
    Link("L. Schaffer on HobbyHow", "www.hobbyhub360.com/index.php/how-to-play-block-ten-solitaire-14362/"),
    Link("Jan Wolter's Experiments", "/article/simplepairs.html")
  ),
  description = "A game of pure luck where you can remove pairs that add to ten, or pairs of face cards, but not tens.",
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOr10JQK,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      lowRank = FoundationLowRank.AnyCard,
      maxCards = 0,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 9,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
