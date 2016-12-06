package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Enable stock (Sn): 0 (No stock)
 *   Tableau initial cards (T0d): -2 (custom)
 *   Custom initial cards (T0ds): U UU UUU UUUU UUUUU UUUUUU UUUUUUU UUUUUUUU UUUUUUUU UUUUUUUU
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 4 (In alternating colors)
 *   Number of waste piles (W0n): 0
 *   Related games (related): morehead, usk
 */
object Somerset extends GameRules(
  id = "somerset",
  completed = true,
  title = "Somerset",
  related = Seq("morehead", "usk"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/somerset.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/somerset.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/somerset.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/somerset.php"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Somerset.htm"),
    Link("Zonora", "www.zonora.com/mysolitaire/rules/klondike/somerset.htm")
  ),
  description = "A ^klondike^ variant without stock or waste. Unlike ^usk^, moves of stacks are not allowed, but spaces can be filled by any card.",
  layout = ":::f|t",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 10,
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "U",
        "UU",
        "UUU",
        "UUUU",
        "UUUUU",
        "UUUUUU",
        "UUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU",
        "UUUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
