package services.help

import models.game.rules._
import play.api.i18n.{Messages, Lang}
import utils.NumberUtils

object TableauHelpService {
  private[this] val defaults = TableauRules()

  def tableau(rules: TableauRules)(implicit lang: Lang) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          Messages("help.tableau.single.cards.empty")
        } else if(i == 1) {
          Messages("help.tableau.single.cards.single")
        } else {
          Messages("help.tableau.single.cards.multiple", NumberUtils.toWords(i))
        }
        case ic => throw new NotImplementedError(ic.toString)
      }
      case x => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          Messages("help.tableau.multiple.cards.empty", NumberUtils.toWords(x, properCase = true))
        } else if(i == 1) {
          Messages("help.tableau.multiple.cards.single", NumberUtils.toWords(x, properCase = true))
        } else {
          Messages("help.tableau.multiple.cards.multiple", NumberUtils.toWords(x, properCase = true), NumberUtils.toWords(i))
        }
        case InitialCards.PileIndex => Messages("help.tableau.multiple.cards.pile.index", NumberUtils.toWords(x, properCase = true))
        case InitialCards.RestOfDeck => Messages("help.tableau.multiple.cards.rest.of.deck", NumberUtils.toWords(x, properCase = true))
        case InitialCards.Custom => Messages("help.tableau.multiple.cards.custom", NumberUtils.toWords(x, properCase = true))
      }
    }
    ret += piles

    if (rules.uniqueRanks) {
      ret += Messages("help.tableau.unique.ranks")
    }

    rules.cardsFaceDown match {
      case TableauFaceDownCards.AllButOne => ret += "help.tableau.cards.face.down.all.but.one"
      case TableauFaceDownCards.Count(i) => i match {
        case 0 => ret += Messages("help.tableau.cards.face.down.none")
        case 1 => ret += Messages("help.tableau.cards.face.down.single")
        case _ => ret += Messages("help.tableau.cards.face.down.multiple", NumberUtils.toWords(i))
      }
      case TableauFaceDownCards.EvenNumbered => ret += Messages("help.tableau.cards.face.down.even.numbered")
      case TableauFaceDownCards.OddNumbered => ret += Messages("help.tableau.cards.face.down.odd.numbered")
    }

    ret += MatchRuleHelpService.toWords(rules.emptyFilledWith)

    if(rules.rankMatchRuleForBuilding != RankMatchRule.None && rules.suitMatchRuleForBuilding != SuitMatchRule.None) {
      ret += Messages("help.tableau.build.none")
    } else {
      ret += Messages(
        "help.tableau.build.rank.and.suit.match.rules",
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    if(rules.rankMatchRuleForMovingStacks != RankMatchRule.None && rules.suitMatchRuleForMovingStacks != SuitMatchRule.None) {
      ret += Messages("help.tableau.move.stacks.none")
    } else {
      ret += Messages(
        "help.tableau.move.stacks.rank.and.suit.match.rules",
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        rules.name + " " + NumberUtils.toWords(rules.setNumber + 1, properCase = true)
      } else {
        rules.name
      }
    }

    name -> ret.toSeq
  }
}
