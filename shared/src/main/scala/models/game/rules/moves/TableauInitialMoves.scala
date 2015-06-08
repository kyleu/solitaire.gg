package models.game.rules.moves

import models.game.GameState
import models.game.rules.{ GameRules, InitialCards, TableauFaceDownCards }

object TableauInitialMoves {
  def performInitialMoves(rules: GameRules, gameState: GameState) = {
    rules.tableaus.foreach { tr =>
      val prefix = if (tr.setNumber == 0) {
        "tableau-"
      } else {
        "tableau" + (tr.setNumber + 1) + "-"
      }
      tr.initialCards match {
        case InitialCards.Count(i) => (1 to i).foreach { row =>
          (1 to tr.numPiles).foreach { col =>
            val reveal = tr.cardsFaceDown match {
              case TableauFaceDownCards.Count(count) => row > count
              case TableauFaceDownCards.AllButOne => row == i
              case TableauFaceDownCards.EvenNumbered => row % 2 != 0
              case TableauFaceDownCards.OddNumbered => row % 2 == 0
            }
            gameState.addCardsFromDeck(1, prefix + col, reveal = reveal, uniqueRanks = tr.uniqueRanks)
          }
        }

        case InitialCards.PileIndex => (1 to tr.numPiles).foreach { row =>
          (1 to tr.numPiles).foreach { col =>
            if (col < row) {
              Unit // no op
            } else if (row == col && tr.cardsFaceDown == TableauFaceDownCards.AllButOne) {
              gameState.addCardsFromDeck(1, prefix + row, reveal = true, uniqueRanks = tr.uniqueRanks)
            } else {
              val reveal = tr.cardsFaceDown match {
                case TableauFaceDownCards.Count(count) => row > count
                case TableauFaceDownCards.AllButOne => false
                case TableauFaceDownCards.EvenNumbered => row % 2 != 0
                case TableauFaceDownCards.OddNumbered => row % 2 == 0
              }
              gameState.addCardsFromDeck(1, prefix + col, reveal = reveal, uniqueRanks = tr.uniqueRanks)
            }
          }
        }

        case InitialCards.RestOfDeck => gameState.deck.cards.indices.foreach { i =>
          val row = (i / tr.numPiles) + 1
          val col = (i % tr.numPiles) + 1
          val reveal = tr.cardsFaceDown match {
            case TableauFaceDownCards.Count(count) => row > count
            case TableauFaceDownCards.AllButOne => gameState.deck.cards.size <= tr.numPiles
            case TableauFaceDownCards.EvenNumbered => row % 2 != 0
            case TableauFaceDownCards.OddNumbered => row % 2 == 0
          }
          gameState.addCardsFromDeck(1, prefix + col, reveal = reveal, uniqueRanks = tr.uniqueRanks)
        }

        case InitialCards.Custom =>
          val maxLength = tr.customInitialCards.map(_.length).max
          (1 to maxLength).foreach { row =>
            tr.customInitialCards.zipWithIndex.foreach { cards =>
              if (cards._1.length >= row) {
                val reveal = cards._1(row - 1) match {
                  case 'U' => true
                  case 'D' => false
                  case _ => throw new IllegalStateException()
                }
                gameState.addCardsFromDeck(1, prefix + (cards._2 + 1), reveal = reveal, uniqueRanks = tr.uniqueRanks)
              }
            }
          }
      }
    }
  }
}
