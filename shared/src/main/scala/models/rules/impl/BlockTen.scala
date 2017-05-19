package models.rules.impl

import models.rules._

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
  layout = "sf|t",
  victoryCondition = VictoryCondition.AllButFourCardsOnFoundation,
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToTenOr10JQK,
  stock = Some(StockRules(dealTo = StockDealTo.Never, maximumDeals = Some(1))),
  foundations = Seq(FoundationRules(numPiles = 4, lowRank = FoundationLowRank.AnyCard, maxCards = 0, autoMoveCards = true)),
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
