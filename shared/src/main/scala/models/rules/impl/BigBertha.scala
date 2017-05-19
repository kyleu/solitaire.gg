package models.rules.impl

import models.card.Rank
import models.rules._

object BigBertha extends GameRules(
  id = "bigbertha",
  completed = true,
  title = "Big Bertha",
  links = Seq(
    Link("Pretty Good Solitaire", "www.goodsol.com/pgshelp/big_bertha.htm"),
    Link("Betsy Gallup on eHow", "www.ehow.com/list_5904884_rules-big-bertha-card-game.html")
  ),
  layout = ":.wf:f|t",
  deckOptions = DeckOptions(numDecks = 2),
  waste = Some(WasteRules(name = "Reserve")),
  foundations = Seq(
    FoundationRules(
      name = "Main Foundation",
      numPiles = 8,
      maxCards = 12,
      autoMoveCards = true
    ),
    FoundationRules(
      name = "Kings Foundation",
      setNumber = 1,
      lowRank = FoundationLowRank.SpecificRank(Rank.King),
      suitMatchRule = SuitMatchRule.Any,
      rankMatchRule = RankMatchRule.Equal,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 15,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0)
    )
  )
)
