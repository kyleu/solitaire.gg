//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(
//    selectCardConstraint = Some(Constraints.alternatingRankToFoundation),
//    dragFromConstraint = Some(Constraints.topCardOnly),
//    dragToConstraint = Some(Constraints.never),
//    selectCardAction = Some(SelectCardActions.drawToPile(1, "foundation"))
//  ))

//    Pile("foundation", "foundation", PileOptionsHelper.foundation.combine(PileOptions(
//      cardsShown = Some(4), direction = Some("r"), dragToConstraint = Some(Constraints.alternatingRank)
//    ))),
//    Pile("stock", "stock", PileOptionsHelper.stock(1, "foundation", None).combine(PileOptions(
//      cardsShown = Some(16),
//      direction = Some("r"),
//      selectPileConstraint = Some(Constraints.never)
//    )))
//  )
