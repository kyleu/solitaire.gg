package services.help

import models.game.rules._
import utils.NumberUtils

object TableauHelpService {
  def tableau(rules: TableauRules) = {
    val ret = collection.mutable.ArrayBuffer.empty[String]

    val piles = rules.numPiles match {
      case 1 => if(rules.initialCards == InitialCards.Count(0)) {
        "A single empty tableau pile."
      } else {
        "A single tableau pile with " + rules.initialCards + " initial cards."
      }
      case x =>
        NumberUtils.toWords(x, properCase = true) + (if(rules.initialCards == InitialCards.Count(0)) {
          " empty tableau piles."
        } else {
          rules.initialCards match {
            case InitialCards.Count(i) =>
              val plural = if(i == 1) { "" } else { "s" }
              " tableau piles with " + NumberUtils.toWords(i) + " initial card" + plural + " dealt to each."
            case InitialCards.PileIndex => " tableau piles with one card dealt to the first pile, two to the second, and so on."
            case InitialCards.RestOfDeck => " tableau piles with the rest of the cards in the deck dealt to them."
            case InitialCards.Custom => " tableau piles with a custom number of cards dealt to them."
            case ic => " Not Implemented [" + ic.toString + "]"
          }
        })
    }
    ret += piles

    if(rules.uniqueRanks) {
      ret += "Each pile will not contain any duplicate ranks."
    }

    rules.cardsFaceDown match {
      case TableauFaceDownCards.AllButOne => ret += "The top card of each pile is turned face-up."
      case TableauFaceDownCards.Count(i) => i match {
        case 1 => ret += "The first card in each pile is turned face-up."
        case _ => ret += "The first " + NumberUtils.toWords(i) + " cards in each pile are turned face-up."
      }
      case TableauFaceDownCards.EvenNumbered => ret += "Each even-numbered card in each pile is face-up."
      case TableauFaceDownCards.OddNumbered => ret += "Each odd-numbered card in each pile is face-up."
    }

    ret += "An empty pile may " + (rules.emptyFilledWith match {
      case TableauFillEmptyWith.Aces => "be filled with any Ace."
      case TableauFillEmptyWith.Any => "be filled with any card."
      case TableauFillEmptyWith.Kings => "be filled with any King."
      case TableauFillEmptyWith.KingsOrAces => "be filled with any King or Ace."
      case TableauFillEmptyWith.KingsUntilStockEmpty => "be filled with any King until the stock is empty."
      case TableauFillEmptyWith.None => "not be filled."
      case TableauFillEmptyWith.Sevens => "be filled with any Seven."
    })

    rules.suitMatchRuleForBuilding match {
      case SuitMatchRule.None => ret += "No cards may be built on the tableau."
      case sb => rules.rankMatchRuleForBuilding match {
        case RankMatchRule.None => ret += "No cards may be built on the tableau."
        case rb => ret += "Cards that are " + sb.toWords + " and " + rb.toWords + " may be added to these piles."
      }
    }

    rules.name -> ret.toSeq
  }
}
