package models.settings

import models.graphql.{CommonSchema, GraphQLContext}
import sangria.macros.derive._
import sangria.schema._

object SettingsSchema {
  implicit val cardBackEnum = CommonSchema.deriveStringEnumeratumType(name = "CardBack", values = CardBack.values)
  implicit val cardBlankEnum = CommonSchema.deriveStringEnumeratumType(name = "CardBlank", values = CardBlank.values)
  implicit val cardFacesEnum = CommonSchema.deriveStringEnumeratumType(name = "CardFaces", values = CardFaces.values)
  implicit val cardLayoutEnum = CommonSchema.deriveStringEnumeratumType(name = "CardLayout", values = CardLayout.values)
  implicit val cardRanksEnum = CommonSchema.deriveStringEnumeratumType(name = "CardRanks", values = CardRanks.values)
  implicit val cardSetEnum = CommonSchema.deriveStringEnumeratumType(name = "CardSet", values = CardSet.values)
  implicit val cardSuitsEnum = CommonSchema.deriveStringEnumeratumType(name = "CardSuits", values = CardSuits.values)
  implicit val languageEnum = CommonSchema.deriveStringEnumeratumType(name = "Language", values = Language.values)
  implicit val menuPositionEnum = CommonSchema.deriveStringEnumeratumType(name = "MenuPosition", values = MenuPosition.values)

  implicit val settingsType: OutputType[Settings] = deriveObjectType[GraphQLContext, Settings](
    ObjectTypeDescription("The settings for this user.")
  )
}
