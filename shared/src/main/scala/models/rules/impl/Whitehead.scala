package models.rules.impl

import models.rules._

object Whitehead extends GameRules(
  id = "whitehead",
  completed = true,
  title = "Whitehead",
  links = Seq(
    Link("Solitaire Whizz", "www.solitairewhizz.com/how-to-play/whitehead.shtml"),
    Link("Solitaire Central", "www.solitairecentral.com/rules/Whitehead.html"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/whitehead.html"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/whitehead.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Whitehead.html"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/whitehead.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/whitehead.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/whitehead.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Whitehead.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Whitehead.html.en")
  ),
  layout = "swf|t",
  stock = Some(StockRules(maximumDeals = Some(1))),
  waste = Some(WasteRules()),
  foundations = Seq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = Seq(TableauRules(
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameColor,
    suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit
  ))
)
