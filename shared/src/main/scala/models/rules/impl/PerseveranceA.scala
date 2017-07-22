package models.rules.impl

import models.rules._

object PerseveranceA extends GameRules(
  id = "perseverancea",
  completed = true,
  title = "Perseverance A",
  like = Some("cruel"),
  related = Seq("perseveranceb"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Perseverance_(solitaire)"),
    Link("An 1898 Description", "howtoplaysolitaire.blogspot.com/2010/06/perseverance-single-deck-solitaire-game.html"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/perseverance.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Perseverance.htm")
  ),
  layout = "::::f|t",
  foundations = IndexedSeq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      autoMoveCards = true
    )
  ),
  tableaus = IndexedSeq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
