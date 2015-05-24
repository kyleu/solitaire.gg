package parser.politaire

import models.game.rules.{FillEmptyWith, RankMatchRule, SuitMatchRule, InitialCards}

trait ParserGameHelper { this: GameRulesParser =>
  protected[this] def getInitialCards(i: Int) = i match {
    case -1 => InitialCards.PileIndex
    case -3 => InitialCards.RestOfDeck
    case -2 => InitialCards.Custom
    case x => InitialCards.Count(x)
  }

  protected[this] def getSuitMatchRule(i: Int) = i match {
    case 0 => SuitMatchRule.None
    case 1 => SuitMatchRule.SameSuit
    case 2 => SuitMatchRule.DifferentSuits
    case 3 => SuitMatchRule.SameColor
    case 4 => SuitMatchRule.AlternatingColors
    case 5 => SuitMatchRule.Any
    case x => throw new IllegalArgumentException(x.toString)
  }

  protected[this] def getRankMatchRule(i: Int) = i match {
    case 0 => RankMatchRule.None
    case 128 => RankMatchRule.Up
    case 32 => RankMatchRule.Down
    case 64 => RankMatchRule.Equal
    case 160 => RankMatchRule.UpOrDown
    case 96 => RankMatchRule.Any
    case 256 => RankMatchRule.UpBy2
    case 16 => RankMatchRule.DownBy2
    case 512 => RankMatchRule.UpBy3
    case 8 => RankMatchRule.DownBy3
    case 1024 => RankMatchRule.UpBy4
    case 4 => RankMatchRule.DownBy4
    case 8192 => RankMatchRule.UpByPileIndex
    case 8191 => RankMatchRule.Any
    case x => throw new IllegalArgumentException(x.toString)
  }

  protected[this] def getFillEmptyWith(i: Int) = i match {
    case 0 => FillEmptyWith.Any
    case 1 => FillEmptyWith.Kings
    case 2 => FillEmptyWith.KingsUntilStockEmpty
    case 4 => FillEmptyWith.None
    case 5 => FillEmptyWith.None
    case 7 => FillEmptyWith.Aces
    case 8 => FillEmptyWith.Sevens
    case 9 => FillEmptyWith.KingsOrAces
  }
}
