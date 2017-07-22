package models.rules.impl

import models.card.Rank
import models.rules._

object Chessboard extends GameRules(
  id = "chessboard",
  completed = true,
  title = "Chessboard",
  like = Some("fortress"),
  related = Seq("lasker", "castlesend"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/chessboard.htm"),
    Link("Solsuite Solitaire", "www.solsuite.com/games/chessboard.htm"),
    Link("BVS Solitaire Collection", "www.bvssolitaire.com/rules/chessboard.htm"),
    Link("Solavant Solitaire", "www.solavant.com/solitaire/chessboard.php"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Chessboard.html"),
    Link("Rapture Technologies KingSol", "www.rapturetech.com/KingSol/Rules/Chessboard.htm"),
    Link("Xolitaire", "www.escapedivision.com/xolitaire/en/games/chessboard.html"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Chessboard.html.en"),
    Link("L. Schaffer on HobbyHub", "www.hobbyhub360.com/index.php/how-to-play-chessboard-solitaire-14077/")
  ),
  layout = ":::f|t",
  deckOptions = DeckOptions(lowRank = Rank.Unknown),
  foundations = IndexedSeq(FoundationRules(numPiles = 4, autoMoveCards = true)),
  tableaus = IndexedSeq(TableauRules(
    numPiles = 10,
    initialCards = InitialCards.RestOfDeck,
    cardsFaceDown = TableauFaceDownCards.Count(0),
    suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
    rankMatchRuleForBuilding = RankMatchRule.UpOrDown,
    wrap = true,
    suitMatchRuleForMovingStacks = SuitMatchRule.None
  ))
)
