//  private[this] val drawPiles = (1 to 8).map("tableau-" + _).toSeq
//
//  private[this] val stockOptions = new PileOptions(
//    cardsShown = Some(1),
//    selectCardConstraint = Some(Constraints.topCardOnly),
//    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
//  )
//
//  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
//    dragToConstraint = Some(Constraints.klondikeTableauDragTo(None))
//  )
