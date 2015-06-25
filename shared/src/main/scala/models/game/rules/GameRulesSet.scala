package models.game.rules

import models.game.pile.Layouts
import models.game.rules.custom._
import models.game.rules.generated._
import models.game.rules.custom.{ KlondikeOneCard, CustomGameRules }

object GameRulesSet {
  val completed = Seq(
    AceOfHearts,
    AlexanderTheGreat,
    Batsford,
    BeleagueredCastle,
    Bisley,
    Bunker,
    Canfield,
    DoubleFreeCell,
    DoubleKlondike,
    DoublePyramid,
    FlowerGarden,
    FourteenOut,
    FortyThieves,
    FreeCell,
    Gargantua,
    GoldMine,
    Golf,
    Gypsy,
    KingAlbert,
    Kingsley,
    Klondike,
    KlondikeOneCard,
    Nestor,
    Pyramid,
    RoyalRendezvous,
    SevenDevils,
    Spider,
    Tarantula,
    Trigon,
    TripleFreeCell,
    TripleKlondike,
    TripleTriangle,
    Westcliff,
    Whitehorse,
    Zerline
  )

  val all = (GeneratedGameRules.all ++ CustomGameRules.all).sortBy(_.id)
  val allById = all.map(x => x.id -> x).toMap

  val favorites = Seq(Klondike, FreeCell, Pyramid, Canfield, Spider)
  val inProgress = Layouts.layouts.keys.toList.sorted.map(r => GameRulesSet.allById(r)).filterNot(r => completed.contains(r))
  lazy val unfinished = GameRulesSet.all.filterNot(r => completed.contains(r) || inProgress.contains(r))
}
