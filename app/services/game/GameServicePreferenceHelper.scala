package services.game

trait GameServicePreferenceHelper { this: GameService =>
  protected[this] def handleSetPreference(name: String, value: String) = name match {
    case "auto-flip" => autoFlipOption = value == "true"
    case _ => // no op
  }
}
