package models.card

sealed trait Color

case object Red extends Color
case object Black extends Color
case object Green extends Color
case object Blue extends Color
case object Colorless extends Color
case object UnknownColor extends Color
