package models.rules.impl

import models.rules._

object StreetAndAlleys extends GameRules(
  id = "streetsandalleys",
  completed = true,
  title = "Street and Alleys",
  like = Some("beleagueredcastle"),
  related = Seq("stronghold", "penelopesweb", "privatelane"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/streets_and_alleys.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/StreetsAndAlleys.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/streets_and_alleys.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/streets-and-alleys.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/StreetsandAlleys.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/streets_and_alleys.php"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Streets_And_Alleys.html.en"),
    Link("Bicycle", "www.bicyclecards.ca/game-rules/streets-and-alleys/185.php?page_id=32"),
    Link("Elton Gahr on HobbyHub", "www.hobbyhub360.com/index.php/solitaire-how-to-play-streets-and-alleys-13643/"),
    Link("Swoop", "www.swoopsoftware.com/solitaire_rules/streets_and_alleys.html")
  ),
  layout = "::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
