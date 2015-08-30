// Generated rules for Solitaire.gg.
package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation initial cards (F0d): 0 (None)
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -3 (Fill rows with rest of deck)
 *   Tableau piles (T0n): 8
 *   Tableau suit match rule for building (T0s): 5 (Regardless of suit)
 *   Number of waste piles (W0n): 0
 *   Similar to (like): beleagueredcastle
 *   Related games (related): stronghold, penelopesweb, privatelane
 *   Enable super moves, whatever those are (supermoves): 1
 */
object StreetAndAlleys extends GameRules(
  id = "streetsandalleys",
  completed = false,
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
  description = "A more difficult variation of ^beleagueredcastle^ that starts with no cards dealt into the foundation.",
  layout = "f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 8,
      initialCards = InitialCards.RestOfDeck,
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)