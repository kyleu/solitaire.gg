package services.help

import models.game.rules._
import utils.NumberUtils

object TableauHelpService {
  private[this] val defaults = TableauRules()

  def tableau(rules: TableauRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if (rules.initialCards == InitialCards.Count(0)) {
        "A single empty tableau pile."
      } else {
        "A single tableau pile with " + rules.initialCards + " initial cards."
      }
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if (rules.initialCards == InitialCards.Count(0)) {
          " empty tableau piles."
        } else {
          rules.initialCards match {
            case InitialCards.Count(i) =>
              val plural = if (i == 1) { "" } else { "s" }
              " tableau piles with " + NumberUtils.toWords(i) + " initial card" + plural + " dealt to each."
            case InitialCards.PileIndex => " tableau piles with one card dealt to the first pile, two to the second, and so on."
            case InitialCards.RestOfDeck => " tableau piles with the rest of the cards in the deck dealt to them."
            case InitialCards.Custom => " tableau piles with a custom number of cards dealt to them."
            case ic => " Not Implemented [" + ic.toString + "]"
          }
        })
    }
    ret += piles

    if (rules.uniqueRanks) {
      ret += "Each pile will not contain any duplicate ranks."
    }

    rules.cardsFaceDown match {
      case TableauFaceDownCards.AllButOne => ret += "The top card of each pile is turned face-up."
      case TableauFaceDownCards.Count(i) => i match {
        case 0 => ret += "All cards are face-up."
        case 1 => ret += "The first card in each pile is turned face-up."
        case _ => ret += "The first " + NumberUtils.toWords(i) + " cards in each pile are turned face-up."
      }
      case TableauFaceDownCards.EvenNumbered => ret += "Each even-numbered card is face-up."
      case TableauFaceDownCards.OddNumbered => ret += "Each odd-numbered card is face-up."
    }

    ret += rules.emptyFilledWith.toWords

    rules.suitMatchRuleForBuilding match {
      case SuitMatchRule.None => ret += "No cards may be built on the tableau."
      case sb => rules.rankMatchRuleForBuilding match {
        case RankMatchRule.None => ret += "No cards may be built on the tableau."
        case rb => ret += "Cards that are " + rb.toWords + " and " + sb.toWords + " may be added to these piles."
      }
    }

    rules.suitMatchRuleForMovingStacks match {
      case SuitMatchRule.None => ret += "No cards may be moved from the tableau."
      case sb => rules.rankMatchRuleForMovingStacks match {
        case RankMatchRule.None => ret += "No cards may be moved from the tableau."
        case rb => ret += "Sequences of cards that are each " + rb.toWords + " and " + sb.toWords + " may be moved from the tableau."
      }
    }

    if(rules.uniqueRanks) {
      ret += "The piles are dealt in such a way that no two cards in the same pile have the same rank."
    }

    val name = if(rules.setNumber == 0) {
      rules.name
    } else {
      if(rules.name == defaults.name) {
        rules.name + " " + NumberUtils.toWords(rules.setNumber + 1, properCase = true)
      } else {
        rules.name
      }
    }

    name -> ret.toSeq
  }
}
