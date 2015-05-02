//  private[this] val drawPiles = (1 to 10).map("tableau-" + _).toSeq
//
//  private[this] val stockOptions = new PileOptions(
//    cardsShown = Some(1),
//    selectCardConstraint = Some(Constraints.pilesNonEmpty((1 to 10).map(i => "tableau-" + i): _*)),
//    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
//  )
//
//  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
//    dragToConstraint = Some(Constraints.lowerRank),
//    dragFromConstraint = Some(Constraints.descendingSequenceSameSuit)
//  )
//
//  private[this] val piles = {
//    List(new Pile("stock", "stock", stockOptions)) ++
//      (1 to 8).map(i => Pile("foundation-" + i, "foundation", PileOptionsHelper.foundation)) ++
//      (1 to 10).map(i => Pile("tableau-" + i, "tableau", tableauOptions))
//  }
//
//  private[this] val deck = newShuffledDecks(2)
