//  private[this] val cellOptions = Pile.options.combine(PileOptions(
//    cardsShown = Some(1),
//    selectCardConstraint = Some(Constraints.klondikeSelectCard),
//    dragToConstraint = Some(Constraints.empty),
//    dragFromConstraint = Some(Constraints.topCardOnly)
//  ))
//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(dragFromConstraint = Some(Constraints.klondikeDragFrom)))
//
//  private[this] val piles = List(
//    Pile("cell-1", "pile", cellOptions),
//    Pile("cell-2", "pile", cellOptions),
//    Pile("cell-3", "pile", cellOptions),
//    Pile("cell-4", "pile", cellOptions),
//
//    Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
//    Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
//    Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
//    Pile("foundation-4", "foundation", PileOptionsHelper.foundation),
//
//    Pile("tableau-1", "tableau", tableauOptions),
//    Pile("tableau-2", "tableau", tableauOptions),
//    Pile("tableau-3", "tableau", tableauOptions),
//    Pile("tableau-4", "tableau", tableauOptions),
//    Pile("tableau-5", "tableau", tableauOptions),
//    Pile("tableau-6", "tableau", tableauOptions),
//    Pile("tableau-7", "tableau", tableauOptions),
//    Pile("tableau-8", "tableau", tableauOptions)
//  )
//
//  private[this] val deck = newShuffledDecks()
//
//  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)
//
//  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
