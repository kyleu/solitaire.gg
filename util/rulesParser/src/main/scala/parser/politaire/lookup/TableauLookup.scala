package parser.politaire.lookup

import parser.politaire.PolitaireParserHelper

object TableauLookup {
  val titles = Seq(
    "T0Nm" -> "Tableau name",
    "T0n" -> "Tableau piles",
    "T0cardsShown" -> "Cards shown",
    "T0d" -> "Tableau initial cards",
    "T0ds" -> "Custom initial cards",
    "T0df" -> "Tableau cards face down",
    "T0s" -> "Tableau suit match rule for building",
    "T0r" -> "Tableau rank match rule for building",
    "T0w" -> "Tableau wraps from king to ace",
    "T0ts" -> "Tableau suit match rule for moving stacks",
    "T0tr" -> "Tableau rank match rule for moving stacks",
    "T0o" -> "May move to non-empty tableau from",
    "T0af" -> "Auto-fill an empty tableau from",
    "T0f" -> "Empty tableau is filled with",
    "T0fo" -> "Empty tableau is filled from",
    "T0m" -> "Maximum cards per tableau",
    "T0dd" -> "Tableau action during deal",
    "T0fx" -> "Tableau action after deal",
    "T0dc" -> "Piles with low cards at bottom"
  )

  val translations = Map(
    "T0d" -> Map(
      0 -> "None", 1 -> "1 card", 2 -> "2 cards", 3 -> "3 cards", 4 -> "4 cards", 5 -> "5 cards", 6 -> "6 cards", 7 -> "7 cards", 8 -> "8 cards",
      9 -> "9 cards", 10 -> "10 cards", 11 -> "11 cards", 12 -> "12 cards", -1 -> "1 to n cards", -3 -> "Fill rows with rest of deck", -2 -> "custom"
    ),
    "TOdf" -> Map(
      0 -> "None", 1 -> "1 per stack", 2 -> "2 per stack", 3 -> "3 per stack", 4 -> "4 per stack", 100 -> "All but one per stack",
      101 -> "Even numbered cards", 102 -> "Odd numbered cards"
    ),
    "T0s" -> Map(
      0 -> "May not build", 1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"
    ),
    "T0r" -> Map(
      0 -> "May not build", 128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "TOw" -> Map(0 -> "No", 1 -> "Yes"),
    "T0ts" -> Map(
      0 -> "May not build", 1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"
    ),
    "T0tr" -> Map(
      0 -> "May not build", 128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "T0o" -> PolitaireParserHelper.pileTypes,
    "T0af" -> Map(0 -> "Nowhere", 4 -> "Stock", 2 -> "Waste", 6 -> "First waste then stock", 10 -> "First stock then waste", -1 -> "Next tableau column"),
    "T0f" -> Map(
      0 -> "Any card", 5 -> "No card", 1 -> "Kings only", 2 -> "Kings only until stock empty", 7 -> "Aces only", 9 -> "Kings or aces only", 8 -> "Sevens only"
    ),
    "T0fo" -> PolitaireParserHelper.pileTypes,
    "T0m" -> Map(
      0 -> "Unlimited", 1 -> "1 cards", 2 -> "2 cards", 3 -> "3 cards", 4 -> "4 cards", 5 -> "5 cards", 6 -> "6 cards", 7 -> "7 cards", 8 -> "8 cards",
      9 -> "9 cards", 10 -> "10 cards", 11 -> "11 cards", 12 -> "12 cards", 13 -> "13 cards"
    ),
    "T0dd" -> Map(
      0 -> "Do nothing special", 1 -> "Move kings to stack bottoms", 3 -> "Move cards to foundations", 5 -> "Move cards to foundations and replace",
      2 -> "Move cards to empty foundations", 4 -> "Move cards to empty foundations and replace"
    ),
    "T0fx" -> Map(0 -> "Do nothing special", 2 -> "limit piles to two jacks"),
    "T0dc" -> Map(
      0 -> "0 columns", 1 -> "1 columns", 2 -> "2 columns", 3 -> "3 columns", 4 -> "4 columns", 5 -> "5 columns", 6 -> "6 columns", 7 -> "7 columns"
    )
  )
}
