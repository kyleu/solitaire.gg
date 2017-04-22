package phaser.card

import com.definitelyscala.phaser.{BitmapData, Image}
import models.card.{Rank, Red, Suit}
import settings.CardLayout

class CardRender(layout: CardLayout, blank: Image, suitImages: Seq[Image], redRankImages: Seq[Image], blackRankImages: Seq[Image], faceCardImages: Seq[Image]) {
  private[this] val rankWidths = IndexedSeq(0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 1.0, 0.9, 0.9, 0.9, 0.9)

  def renderCard(s: Suit, r: Rank, tex: BitmapData) = {
    tex.draw(blank, 0, 0)

    val suitImage = suitImages(s.index)
    val rankImage = if (s.color == Red) {
      redRankImages(r.index - 2)
    } else {
      blackRankImages(r.index - 2)
    }

    layout match {
      case CardLayout.A =>
        if (r.locs.isEmpty) {
          if (r == Rank.Ace) {
            tex.draw(suitImage, 200, 300, 200, 200)
          } else {
            tex.draw(faceCardImages((s.index * 3) + r.index - 11), 200, 325, 350, 525)
          }
        } else {
          r.locs.foreach { loc =>
            tex.draw(suitImage, (loc._1 * 300) + 50, (loc._2 * 500) + 50, 100, 100);
          }
        }

        val rIdx = r.index - 2
        val rankWidth = rankWidths(rIdx)
        tex.draw(rankImage, 60 * rankWidth, 60, 80, 80)
        tex.draw(suitImage, (60 * rankWidth) + 60, 60, 50, 50)
        tex.draw(suitImage, 60 * rankWidth, 130, 50, 50)
      case CardLayout.B =>
        if (r.locs.isEmpty) {
          if (r == Rank.Ace) {
            tex.draw(suitImage, 200, 300, 200, 200)
          } else {
            tex.draw(faceCardImages((s.index * 3) + r.index - 11), 200, 300, 400, 600)
          }
        } else {
          r.locs.foreach { loc =>
            if (loc._2 > 0.5) {
              suitImage.angle = 180
            }
            tex.draw(suitImage, (loc._1 * 300) + 50, loc._2 * 600, 100, 100)
            if (loc._2 > 0.5) {
              suitImage.angle = 0
            }
          }
        }

        tex.draw(rankImage, 35, 45, 50, 50)
        tex.draw(suitImage, 35, 95, 50, 50)
        rankImage.angle = 180
        suitImage.angle = 180
        tex.draw(rankImage, 365, 565, 50, 50)
        tex.draw(suitImage, 365, 515, 50, 50)
        rankImage.angle = 0
        suitImage.angle = 0
      case _ =>
        throw new IllegalStateException("?: " + layout)
    }
    tex
  }
}
