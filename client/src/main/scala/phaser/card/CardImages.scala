package phaser.card

import com.definitelyscala.phaser.Image
import models.card.{Rank, Suit}
import models.settings.Settings
import phaser.PhaserGame

class CardImages(game: PhaserGame, settings: Settings) {
  private[this] val blank = new Image(game, 0, 0, "card-blank", 0)
  private[this] val back = new Image(game, 0, 0, "card-back", 0)

  private[this] val suitImages = Seq(0, 1, 2, 3).map { i =>
    val ret = new Image(game, 0, 0, "card-suits", i)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    ret
  }

  private[this] val redRankImages = Rank.all.map { r =>
    val ret = new Image(game, 0, 0, "card-ranks", r.index - 2)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    ret
  }

  private[this] val blackRankImages = Rank.all.map { r =>
    val ret = new Image(game, 0, 0, "card-ranks", 13 + r.index - 2)
    ret.anchor.x = 0.5
    ret.anchor.y = 0.5
    ret
  }

  private[this] val faceCardImages = Suit.standard.flatMap { s =>
    Seq(0, 1, 2).map { i =>
      val ret = new Image(game, 0, 0, "card-faces", (s.index * 3) + i)
      ret.anchor.x = 0.5
      ret.anchor.y = 0.5
      ret
    }
  }

  private[this] val renderer = new CardRender(settings.cardLayout, blank, suitImages, redRankImages, blackRankImages, faceCardImages)

  val textures = Suit.standard.flatMap { s =>
    Rank.all.map { r =>
      val tex = game.add.bitmapData(settings.cardSet.w.toDouble, settings.cardSet.h.toDouble)
      r.value.toString + s.value -> renderer.renderCard(s, r, tex)
    }
  }.toMap

  val cardBack = {
    val tex = game.add.bitmapData(settings.cardSet.w.toDouble, settings.cardSet.h.toDouble)
    renderer.renderCardBack(back, tex)
    tex
  }

  val emptyPile = {
    val tex = game.add.bitmapData(settings.cardSet.w.toDouble, settings.cardSet.h.toDouble)
    val opaque = game.add.bitmapData(settings.cardSet.w.toDouble, settings.cardSet.h.toDouble)
    opaque.fill(0, 0, 0, 1)
    renderer.renderEmptyPile(blank, tex, opaque)
    tex
  }

}
