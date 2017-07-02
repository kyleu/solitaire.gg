package models.settings

import models.graphql.{CommonSchema, GraphQLContext}
import sangria.macros.derive._
import sangria.schema._

object SettingsSchema {
  implicit val cardBackEnum = CommonSchema.deriveStringEnumeratumType(name = "CardBack", values = CardBack.values.map(t => t -> t.value).toList)
  implicit val cardBlankEnum = CommonSchema.deriveStringEnumeratumType(name = "CardBlank", values = CardBlank.values.map(t => t -> t.value).toList)
  implicit val cardFacesEnum = CommonSchema.deriveStringEnumeratumType(name = "CardFaces", values = CardFaces.values.map(t => t -> t.value).toList)
  implicit val cardLayoutEnum = CommonSchema.deriveStringEnumeratumType(name = "CardLayout", values = CardLayout.values.map(t => t -> t.value).toList)
  implicit val cardRanksEnum = CommonSchema.deriveStringEnumeratumType(name = "CardRanks", values = CardRanks.values.map(t => t -> t.value).toList)
  implicit val cardSetEnum = CommonSchema.deriveStringEnumeratumType(name = "CardSet", values = CardSet.values.map(t => t -> t.value).toList)
  implicit val cardSuitsEnum = CommonSchema.deriveStringEnumeratumType(name = "CardSuits", values = CardSuits.values.map(t => t -> t.value).toList)
  implicit val languageEnum = CommonSchema.deriveStringEnumeratumType(name = "Language", values = Language.values.map(t => t -> t.value).toList)
  implicit val menuPositionEnum = CommonSchema.deriveStringEnumeratumType(name = "MenuPosition", values = MenuPosition.values.map(t => t -> t.value).toList)

  implicit val settingsType: OutputType[Settings] = deriveObjectType[GraphQLContext, Settings](
    ObjectTypeDescription("The settings for this user.")
  )
}
