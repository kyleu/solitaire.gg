package models.rules.impl

import models.rules._

object GayGordons extends GameRules(
  id = "gaygordons",
  completed = false,
  title = "Gay Gordons",
  links = Seq(
    Link("David Parlett's page", "www.davpar.eu/patience/gaygordons.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Gay_Gordons_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/exit.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/exit.php"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/exit.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Exit.htm"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Gay_Gordons.html.en")
  ),
  layout = "f|r|t",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK,
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      actionAfterDeal = PileAction.LimitToTwoJacks
    )
  ),
  reserves = Some(ReserveRules(initialCards = 2))
)
