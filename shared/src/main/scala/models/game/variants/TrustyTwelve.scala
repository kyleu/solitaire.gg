//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(
//    cardsShown = Some(2),
//    selectCardConstraint = Some(Constraints.never),
//    dragFromConstraint = Some(Constraints.topCardOnly),
//    dragToConstraint = Some(Constraints.lowerRank)
//  ))
//
//  private[this] val piles = List(
//    Pile("stock", "stock", PileOptionsHelper.stock(0, "", None).combine(PileOptions(
//      cardsShown = Some(19),
//      direction = Some("r"),
//      selectCardConstraint = Some(Constraints.topCardOnly),
//      selectCardAction = Some(SelectCardActions.drawToEmptyPiles("tableau"))
//    ))),
//
//    Pile("tableau-1", "tableau", tableauOptions),
//    Pile("tableau-2", "tableau", tableauOptions),
//    Pile("tableau-3", "tableau", tableauOptions),
//    Pile("tableau-4", "tableau", tableauOptions),
//    Pile("tableau-5", "tableau", tableauOptions),
//    Pile("tableau-6", "tableau", tableauOptions),
//
//    Pile("tableau-7", "tableau", tableauOptions),
//    Pile("tableau-8", "tableau", tableauOptions),
//    Pile("tableau-9", "tableau", tableauOptions),
//    Pile("tableau-10", "tableau", tableauOptions),
//    Pile("tableau-11", "tableau", tableauOptions),
//    Pile("tableau-12", "tableau", tableauOptions)
//  )
