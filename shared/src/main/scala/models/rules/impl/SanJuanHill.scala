package models.rules.impl

import models.rules._

/**
 * Original Settings:
 *   Foundation name (F0Nm): Hill
 *   Auto-move cards to foundation (F0a): 1 (Whenever possible)
 *   Foundation initial cards (F0d): -1
 *   Tableau name (T0Nm): Rough Riders
 *   Tableau initial cards (T0d): 4 (4 cards)
 *   Tableau piles (T0n): 10
 *   Tableau suit match rule for building (T0s): 1 (In same suit)
 *   Similar to (like): fortythieves
 *   Number of decks (ndecks): 2 (2 decks)
 */
object SanJuanHill extends GameRules(
  id = "sanjuanhill",
  completed = false,
  title = "San Juan Hill",
  like = Some("fortythieves"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/san_juan_hill.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/SanJuanHill.htm")
  ),
  description = "An easier variant of ^fortythieves^ in which aces are already on the foundation.",
  layout = "swf|t",
  deckOptions = DeckOptions(
    numDecks = 2
  ),
  stock = Some(
    StockRules(
      maximumDeals = Some(1)
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      name = "Hill",
      numPiles = 8,
      initialCards = 8,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Rough Riders",
      numPiles = 10,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  )
)
