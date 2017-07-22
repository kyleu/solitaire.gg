package models.rules.impl

import models.rules._

object WillOTheWisp extends GameRules(
  id = "willothewisp",
  completed = false,
  title = "Will o the Wisp",
  related = Seq("fairmaids"),
  links = Seq(
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/will-o-the-wisp.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/will_o_the_wisp.html"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/WilloTheWisp.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/will_o_the_wisp.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/will_o_the_wisp.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/will_o_the_wisp.php"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Will_O_The_Wisp.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-will-o-the-wisp-solitaire-14333/")
  ),
  layout = "sf|t",
  stock = Some(StockRules(dealTo = StockDealTo.TableauIfNoneEmpty, maximumDeals = Some(1))),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, moveCompleteSequencesOnly = true)),
  tableaus = IndexedSeq(TableauRules(
    initialCards = InitialCards.Count(3),
    suitMatchRuleForBuilding = SuitMatchRule.Any,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
  ))
)
