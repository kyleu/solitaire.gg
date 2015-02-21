package models.game

import java.util.UUID

case class Card(id: String = UUID.randomUUID.toString, r: Rank, s: Suit, var u: Boolean)
