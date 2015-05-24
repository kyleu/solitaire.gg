package services.help

import models.game.Rank
import models.game.rules.{FillEmptyWith, SuitMatchRule, RankMatchRule}
import play.api.i18n.{Messages, Lang}

object MatchRuleHelpService {
  def toWords(rmr: RankMatchRule)(implicit lang: Lang) = rmr match {
    case RankMatchRule.None => Messages("help.rank.match.rule.none")
    case RankMatchRule.Up => Messages("help.rank.match.rule.up")
    case RankMatchRule.Down => Messages("help.rank.match.rule.down")
    case RankMatchRule.Equal => Messages("help.rank.match.rule.equal")
    case RankMatchRule.UpOrDown => Messages("help.rank.match.rule.up.or.down")
    case RankMatchRule.UpBy2 => Messages("help.rank.match.rule.up.by.2")
    case RankMatchRule.DownBy2 => Messages("help.rank.match.rule.down.by.2")
    case RankMatchRule.UpBy3 => Messages("help.rank.match.rule.up.by.3")
    case RankMatchRule.DownBy3 => Messages("help.rank.match.rule.down.by.3")
    case RankMatchRule.UpBy4 => Messages("help.rank.match.rule.up.by.4")
    case RankMatchRule.DownBy4 => Messages("help.rank.match.rule.down.by.4")
    case RankMatchRule.UpByPileIndex => Messages("help.rank.match.rule.up.by.pile.index")
    case RankMatchRule.Any => Messages("help.rank.match.rule.any")
  }

  def toWords(smr: SuitMatchRule)(implicit lang: Lang) = smr match {
    case SuitMatchRule.None => Messages("help.suit.match.rule.none")
    case SuitMatchRule.SameSuit => Messages("help.suit.match.rule.same.suit")
    case SuitMatchRule.DifferentSuits => Messages("help.suit.match.rule.different.suit")
    case SuitMatchRule.SameColor => Messages("help.suit.match.rule.same.color")
    case SuitMatchRule.AlternatingColors => Messages("help.suit.match.rule.alternating.color")
    case SuitMatchRule.Any => Messages("help.suit.match.rule.any")
  }

  def toWords(few: FillEmptyWith, lowRank: Rank, highRank: Rank) = few match {
    case FillEmptyWith.Any => Messages("help.fill.empty.with.any")
    case FillEmptyWith.None => Messages("help.fill.empty.with.none")
    case FillEmptyWith.LowRank => Messages("help.fill.empty.with.rank", lowRank)
    case FillEmptyWith.HighRank => Messages("help.fill.empty.with.rank", highRank)
    case FillEmptyWith.HighRankUntilStockEmpty => Messages("help.fill.empty.with.rank.until.stock.empty", highRank)
    case FillEmptyWith.HighRankOrLowRank => Messages("help.fill.empty.with.high.rank.or.low.rank", lowRank, highRank)
    case FillEmptyWith.Sevens => Messages("help.fill.empty.with.sevens")
  }
}
