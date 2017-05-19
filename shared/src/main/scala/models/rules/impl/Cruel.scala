package models.rules.impl

import models.rules._

object Cruel extends GameRules(
  id = "cruel",
  completed = false,
  title = "Cruel",
  related = Seq("indefatigable", "unusual", "ripplefan", "perseverancea"),
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/cruel.htm"),
    Link("Solitaire Till Dawn", "www.semicolon.com/Solitaire/Rules/Cruel.html"),
    Link("Wikipedia", "en.wikipedia.org/wiki/Cruel_(solitaire)"),
    Link("AisleRiot", "help.gnome.org/users/aisleriot/stable/Cruel.html.en"),
    Link("Dan Fletcher's Strategy Guide.", "ezinearticles.com/?Cruel-Solitaire-Strategy-Guide&id=111462"),
    Link("L. Schaffer's Rules and Strategy Guide", "www.hobbyhub360.com/index.php/how-to-play-cruel-solitaire-14162/"),
    Link("Jan Wolter's Analysis", "/article/cruel.html"),
    Link("An 1898 Description of a game called \"Perseverance\".", "howtoplaysolitaire.blogspot.com/2010/06/perseverance-single-deck-solitaire-game.html")
  ),
  layout = "::f|2t",
  foundations = Seq(FoundationRules(numPiles = 4, initialCards = 4, autoMoveCards = true)),
  tableaus = Seq(
    TableauRules(
      numPiles = 12,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = FillEmptyWith.None
    )
  )
)
