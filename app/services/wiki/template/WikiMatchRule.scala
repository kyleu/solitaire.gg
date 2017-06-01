package services.wiki.template

import models.card.Rank
import models.rules.{FillEmptyWith, RankMatchRule, SuitMatchRule}
import services.wiki.WikiService.messages

object WikiMatchRule {
  def toWords(rmr: RankMatchRule) = rmr match {
    case RankMatchRule.None => messages("help.rank.match.rule.none")
    case RankMatchRule.Up => messages("help.rank.match.rule.up")
    case RankMatchRule.Down => messages("help.rank.match.rule.down")
    case RankMatchRule.Equal => messages("help.rank.match.rule.equal")
    case RankMatchRule.UpOrDown => messages("help.rank.match.rule.up.or.down")
    case RankMatchRule.UpBy2 => messages("help.rank.match.rule.up.by.2")
    case RankMatchRule.DownBy2 => messages("help.rank.match.rule.down.by.2")
    case RankMatchRule.UpBy3 => messages("help.rank.match.rule.up.by.3")
    case RankMatchRule.DownBy3 => messages("help.rank.match.rule.down.by.3")
    case RankMatchRule.UpBy4 => messages("help.rank.match.rule.up.by.4")
    case RankMatchRule.DownBy4 => messages("help.rank.match.rule.down.by.4")
    case RankMatchRule.UpByPileIndex => messages("help.rank.match.rule.up.by.pile.index")
    case RankMatchRule.Any => messages("help.rank.match.rule.any")
  }

  def toWords(smr: SuitMatchRule) = smr match {
    case SuitMatchRule.None => messages("help.suit.match.rule.none")
    case SuitMatchRule.SameSuit => messages("help.suit.match.rule.same.suit")
    case SuitMatchRule.DifferentSuits => messages("help.suit.match.rule.different.suit")
    case SuitMatchRule.SameColor => messages("help.suit.match.rule.same.color")
    case SuitMatchRule.AlternatingColors => messages("help.suit.match.rule.alternating.color")
    case SuitMatchRule.Any => messages("help.suit.match.rule.any")
  }

  def toWords(few: FillEmptyWith, lowRank: Rank, highRank: Rank) = few match {
    case FillEmptyWith.Any => messages("help.fill.empty.with.any")
    case FillEmptyWith.None => messages("help.fill.empty.with.none")
    case FillEmptyWith.LowRank => messages("help.fill.empty.with.rank", lowRank)
    case FillEmptyWith.HighRank => messages("help.fill.empty.with.rank", highRank)
    case FillEmptyWith.HighRankUntilStockEmpty => messages("help.fill.empty.with.rank.until.stock.empty", highRank)
    case FillEmptyWith.HighRankOrLowRank => messages("help.fill.empty.with.high.rank.or.low.rank", lowRank, highRank)
    case FillEmptyWith.Sevens => messages("help.fill.empty.with.sevens")
  }
}
