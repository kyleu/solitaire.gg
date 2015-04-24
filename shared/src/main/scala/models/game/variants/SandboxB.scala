//package models.game.variants
//
//import java.util.UUID
//
//import models.game._
//import models.game.pile.{ PileOptions, Pile, PileOptionsHelper }
//
//object SandboxB extends GameVariant.Description {
//  override val completed = false
//  override val key = "sandboxb"
//  override val name = "SandboxB"
//  override val body = "..."
//  override val maxPlayers = 3
//  override val layouts = Seq(
//    Layout(
//      width = 16.5,
//      height = 2.0,
//      piles = List(
//        PileLocation("waste", 0.6, 1.0)
//      )
//    )
//  )
//}
//
//case class SandboxB(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
//  override val description = SandboxB
//
//  val piles = List(
//    Pile("waste", "waste", PileOptionsHelper.waste.combine(PileOptions(cardsShown = Some(52), direction = Some("r"))))
//  )
//
//  val deck = Deck.fresh()
//
//  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)
//
//  override def initialMoves() = {
//    gameState.addCards(deck.getCards(turnFaceUp = true), "waste", reveal = true)
//  }
//
//  override def isWin = false
//}
