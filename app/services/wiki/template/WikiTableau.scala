package services.wiki.template

import models.rules._
import services.wiki.WikiService
import services.wiki.WikiService.messages

object WikiTableau {
  private[this] val defaults = TableauRules()

  def tableau(rules: TableauRules, deckOptions: DeckOptions) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]
    val loweredName = rules.name.toLowerCase

    val piles = rules.numPiles match {
      case 1 => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          messages("help.piles.single.cards.empty", loweredName)
        } else if (i == 1) {
          messages("help.piles.single.cards.single", loweredName)
        } else {
          messages("help.piles.single.cards.multiple", loweredName, WikiService.numberAsString(i))
        }
        case ic => throw new IllegalStateException(ic.toString)
      }
      case x => rules.initialCards match {
        case InitialCards.Count(i) => if (i == 0) {
          messages("help.piles.multiple.cards.empty", WikiService.numberAsString(x, properCase = true), loweredName)
        } else if (i == 1) {
          messages("help.piles.multiple.cards.single.each", WikiService.numberAsString(x, properCase = true), loweredName)
        } else {
          messages("help.piles.multiple.cards.multiple.each", WikiService.numberAsString(x, properCase = true), loweredName, WikiService.numberAsString(i))
        }
        case InitialCards.PileIndex => messages("help.piles.multiple.cards.pile.index", WikiService.numberAsString(x, properCase = true), loweredName)
        case InitialCards.RestOfDeck => messages("help.piles.multiple.cards.rest.of.deck", WikiService.numberAsString(x, properCase = true), loweredName)
        case InitialCards.Custom => messages("help.piles.multiple.cards.custom", WikiService.numberAsString(x, properCase = true), loweredName)
      }
    }
    ret += piles

    if (rules.uniqueRanks) {
      ret += messages("help.tableau.unique.ranks")
    }

    rules.cardsFaceDown match {
      case TableauFaceDownCards.AllButOne => ret += messages("help.tableau.cards.face.down.all.but.one")
      case TableauFaceDownCards.Count(i) => i match {
        case 0 => ret += messages("help.tableau.cards.face.down.none")
        case 1 => ret += messages("help.tableau.cards.face.down.single")
        case _ => ret += messages("help.tableau.cards.face.down.multiple", WikiService.numberAsString(i))
      }
      case TableauFaceDownCards.EvenNumbered => ret += messages("help.tableau.cards.face.down.even.numbered")
      case TableauFaceDownCards.OddNumbered => ret += messages("help.tableau.cards.face.down.odd.numbered")
    }

    ret += WikiMatchRule.toWords(rules.emptyFilledWith, deckOptions.lowRank, deckOptions.highRank)

    if (rules.rankMatchRuleForBuilding == RankMatchRule.None || rules.suitMatchRuleForBuilding == SuitMatchRule.None) {
      ret += messages("help.tableau.build.none", loweredName)
    } else {
      ret += messages(
        "help.tableau.build.rank.and.suit.match.rules",
        loweredName,
        WikiMatchRule.toWords(rules.rankMatchRuleForBuilding),
        WikiMatchRule.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    if (rules.rankMatchRuleForMovingStacks == RankMatchRule.None || rules.suitMatchRuleForMovingStacks == SuitMatchRule.None) {
      ret += messages("help.tableau.move.stacks.none", loweredName)
    } else {
      ret += messages(
        "help.tableau.move.stacks.rank.and.suit.match.rules",
        loweredName,
        WikiMatchRule.toWords(rules.rankMatchRuleForBuilding),
        WikiMatchRule.toWords(rules.suitMatchRuleForBuilding)
      )
    }

    val name = if (rules.setNumber == 0) {
      rules.name
    } else {
      if (rules.name == defaults.name) {
        s"${rules.name} ${WikiService.numberAsString(rules.setNumber + 1, properCase = true)}"
      } else {
        rules.name
      }
    }

    name -> ret
  }
}
