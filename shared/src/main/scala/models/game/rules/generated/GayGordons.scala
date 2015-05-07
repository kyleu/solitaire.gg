// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

/**
 * Original Settings:
 *   Reserve initial cards (R0d): 2
 *   Reserve cards face down (R0df): 0
 *   Number of reserve piles (R0n): 1
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): 5 (5 cards)
 *   Empty tableau is filled with (T0f): 5 (No card)
 *   Tableau action after deal (T0fx): 2 (limit piles to two jacks)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 0 (May not build)
 *   Number of waste piles (W0n): 0
 *   Left mouse interface function (leftfunc): 0x1
 *   Card removal method (pairs): 23 (Remove pairs adding to 11, J-J or Q-K)
 */
object GayGordons extends GameRules(
  id = "gaygordons",
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
  description = "A pair removal game where you remove pairs that add to 11, Kings with Queens, or Jacks together.",
  cardRemovalMethod = CardRemovalMethod.RemovePairsAddingToElevenOrJPairOrQK,
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Count(5),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      actionAfterDeal = PileAction.LimitToTwoJacks
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 2,
      cardsFaceDown = 0
    )
  ),
  complete = false
)

