package models.rules.impl

import models.rules._

object Crescent extends GameRules(
  id = "crescent",
  completed = true,
  title = "Crescent",
  like = Some("rainbowfan"),
  related = Seq("crescentfour"),
  links = Seq(
    Link("Wikipedia", "en.wikipedia.org/wiki/Crescent_(solitaire)"),
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/crescent.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/crescent.html"),
    Link("Solitaire Game Rules.com", "solitaire-game-rules.com/games/crescent.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/crescent.htm"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Crescent.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/crescent.htm"),
    Link("Lena Games", "www.lenagames.com/bp_files/rul/crescent.htm")
  ),
  layout = "::f::::f|t",
  deckOptions = DeckOptions(numDecks = 2),
  foundations = Seq(
    FoundationRules(
      name = "Aces Foundation",
      numPiles = 4,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      numPiles = 4,
      lowRank = FoundationLowRank.DeckHighRank,
      initialCardRestriction = Some(FoundationInitialCardRestriction.UniqueSuits),
      initialCards = 4,
      rankMatchRule = RankMatchRule.Down,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 16,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
      wrap = true,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None,
      mayMoveToNonEmptyFrom = Seq("tableau")
    )
  ),
  special = Some(SpecialRules(rotationsAllowed = 3, rotationTopToBottom = false))
)
