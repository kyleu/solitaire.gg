package utils

import models.settings.Settings
import upickle.default._

import scala.util.control.NonFatal

import models.settings.CardBack.{ uPickleReadWriter => cardBackWriter }
import models.settings.CardBlank.{ uPickleReadWriter => cardBlankWriter }
import models.settings.CardFaces.{ uPickleReadWriter => cardFacesWriter }
import models.settings.CardLayout.{ uPickleReadWriter => cardLayoutWriter }
import models.settings.CardRanks.{ uPickleReadWriter => cardRanksWriter }
import models.settings.CardSuits.{ uPickleReadWriter => cardSuitsWriter }
import models.settings.MenuPosition.{ uPickleReadWriter => menuPositionWriter }

object JsonSettingsSerializers {
  def readSettings(s: String) = try {
    read[Settings](s)
  } catch {
    case NonFatal(x) => Settings.default
  }

  def writeSettings(s: Settings, indent: Boolean = true) = if(indent) {
    write(s, indent = 2)
  } else {
    write(s)
  }
}
