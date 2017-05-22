package help

import models.rules._
import utils.Messages

object TableauHelpService {
  private[this] val defaults = TableauRules()

  def tableau(rules: TableauRules, deckOptions: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          Messages("help.piles.single.cards.empty", loweredName)
        } else if (i == 1) {
          Messages("help.piles.single.cards.single", loweredName)
        } else {
          Messages("help.piles.single.cards.multiple", loweredName, Messages.numberAsString(i))
        }
        case ic => throw new IllegalStateException(ic.toString)
      }
      case x => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          Messages("help.piles.multiple.cards.empty", Messages.numberAsString(x, properCase = true), loweredName)
        } else if (i == 1) {
          Messages("help.piles.multiple.cards.single.each", Messages.numberAsString(x, properCase = true), loweredName)
        } else {
          Messages("help.piles.multiple.cards.multiple.each", Messages.numberAsString(x, properCase = true), loweredName, Messages.numberAsString(i))
        }
        case InitialCards.PileIndex => Messages("help.piles.multiple.cards.pile.index", Messages.numberAsString(x, properCase = true), loweredName)
        case InitialCards.RestOfDeck => Messages("help.piles.multiple.cards.rest.of.deck", Messages.numberAsString(x, properCase = true), loweredName)
        case InitialCards.Custom => Messages("help.piles.multiple.cards.custom", Messages.numberAsString(x, properCase = true), loweredName)
      }
    }
    ret += piles

    if (rules.uniqueRanks) {
      ret += Messages("help.tableau.unique.ranks")
    }

    rules.cardsFaceDown match {
      case TableauFaceDownCards.AllButOne => ret += Messages("help.tableau.cards.face.down.all.but.one")
      case TableauFaceDownCards.Count(i) => i match {
        case 0 => ret += Messages("help.tableau.cards.face.down.none")
        case 1 => ret += Messages("help.tableau.cards.face.down.single")
        case _ => ret += Messages("help.tableau.cards.face.down.multiple", Messages.numberAsString(i))
      }
      case TableauFaceDownCards.EvenNumbered => ret += Messages("help.tableau.cards.face.down.even.numbered")
      case TableauFaceDownCards.OddNumbered => ret += Messages("help.tableau.cards.face.down.odd.numbered")
    }

    ret += MatchRuleHelpService.toWords(rules.emptyFilledWith, deckOptions.lowRank, deckOptions.highRank)

    if (rules.rankMatchRuleForBuilding == RankMatchRule.None || rules.suitMatchRuleForBuilding == SuitMatchRule.None) {
      ret += Messages("help.tableau.build.none", loweredName)
    } else {
      ret += Messages(
        "help.tableau.build.rank.and.suit.match.rules",
        loweredName,
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    if (rules.rankMatchRuleForMovingStacks == RankMatchRule.None || rules.suitMatchRuleForMovingStacks == SuitMatchRule.None) {
      ret += Messages("help.tableau.move.stacks.none", loweredName)
    } else {
      ret += Messages(
        "help.tableau.move.stacks.rank.and.suit.match.rules",
        loweredName,
        MatchRuleHelpService.toWords(rules.rankMatchRuleForBuilding),
        MatchRuleHelpService.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        s"${rules.name} ${Messages.numberAsString(rules.setNumber + 1, properCase = true)}"
      } else {
        rules.name
      }
    }

    name -> ret
  }
}
