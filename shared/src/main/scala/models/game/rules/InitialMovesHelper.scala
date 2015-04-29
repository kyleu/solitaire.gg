package models.game.rules

import models.game.GameState
import models.game.variants._

object InitialMovesHelper {
  def performInitialMoves(rules: GameRules, state: GameState) = {
    val f = rules.id match {
      case "canfield" => Canfield.initialMoves(state)
//      case "freecell" => FreeCell.initialMoves(state)
      case "golf" => Golf.initialMoves(state)
      case "gypsy" => Gypsy.initialMoves(state)
//      case "klondike" => Klondike.initialMoves(state)
      case "nestor" => Nestor.initialMoves(state)
      case "pyramid" => Pyramid.initialMoves(state)
      case "sandbox" => Sandbox.initialMoves(state)
      case "sandboxb" => SandboxB.initialMoves(state)
      case "spider" => Spider.initialMoves(state)
      case "trustytwelve" => TrustyTwelve.initialMoves(state)
      case "yukon" => Yukon.initialMoves(state)
      case _ => performDefault(rules, state)
    }
  }

  private[this] def performDefault(rules: GameRules, gameState: GameState) = {
    def addCards(numCards: Int, pile: String, reveal: Boolean = false) = {
      gameState.addCards(gameState.deck.getCards(numCards, turnFaceUp = reveal), pile, reveal = reveal)
    }

    rules.tableaus.foreach { tr =>
      tr.initialCards match {
        case InitialCards.Count(i) => throw new NotImplementedError()
        case InitialCards.PileIndex =>
          (1 to tr.numPiles).foreach { row =>
            (1 to tr.numPiles).foreach { col =>
              if (col < row) {
                Unit // no op
              } else if (row == col && tr.cardsFaceDown == TableauFaceDownCards.AllButOne) {
                addCards(1, "tableau-" + row, reveal = true)
              } else {
                val reveal = tr.cardsFaceDown match {
                  case TableauFaceDownCards.Count(i) => row > i
                  case TableauFaceDownCards.AllButOne => false
                  case TableauFaceDownCards.EvenNumbered => row % 2 != 0
                  case TableauFaceDownCards.OddNumbered => row % 2 == 0
                }
                addCards(1, "tableau-" + col, reveal = reveal)
              }
            }
          }
        case InitialCards.RestOfDeck => throw new NotImplementedError()
        case InitialCards.Custom => throw new NotImplementedError()
      }

      rules.stock.foreach { s =>
        gameState.addCards(gameState.deck.getCards().reverse, "stock")
      }
    }
  }
}
