package parser.politaire.lookup

object PyramidLookup {
  val titles = Seq(
    "P0Nm" -> "Pyramid name",
    "P0type" -> "Pyramid type",
    "P0size" -> "Pyramid size",
    "P0df" -> "Pyramid cards dealt face down",
    "P0s" -> "Pyramid suit match rule for building",
    "P0r" -> "Pyramid rank match rule for building",
    "P0w" -> "Pyramid wraps from king to ace",
    "P0ts" -> "Pyramid suit match rule for moving stacks",
    "P0tr" -> "Pyramid rank match rule for moving stacks",
    "P0o" -> "May move to non-empty pyramid from",
    "P0f" -> "Empty pyramid is filled with",
    "P0fo" -> "Empty pyramid is filled from"
  )

  val translations = Map(
    "P0type" -> Map(1 -> "Standard Pyramid", 2 -> "Inverted Pyramid", 3 -> "Custom"),
    "P0size" -> Map(
      2 -> "Height 2 (3 cards total)", 3 -> "Height 3 (6 cards total)", 4 -> "Height 4 (10 cards total)", 5 -> "Height 5 (15 cards total)",
      6 -> "height 6 (21 cards total)", 7 -> "Height 7 (28 cards total)", 8 -> "Height 8 (36 cards total)", 9 -> "Height 9 (45 cards total)",
      10 -> "Height 10 (55 cards total)", 11 -> "Height 11 (66 cards total)", 12 -> "Height 12 (78 cards total)"
    ),
    "P0df" -> Map(
      0 -> "None", 1 -> "Top row", 2 -> "Top two rows", 3 -> "Top three rows", 4 -> "Top four rows",
      100 -> "All but last row", 101 -> "Even numbered rows", 102 -> "Odd numbered rows"
    ),
    "P0s" -> Map(
      0 -> "May not build", 1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"
    ),
    "P0r" -> Map(
      0 -> "May not build", 128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "POw" -> Map(0 -> "No", 1 -> "Yes"),
    "P0ts" -> Map(
      0 -> "May not build", 1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"
    ),
    "P0tr" -> Map(
      0 -> "May not build", 128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "P0o" -> Map(2 -> "Waste", 4 -> "Tableau", 128 -> "Pyramid", 8 -> "Foundation"),
    "P0f" -> Map(
      0 -> "Any card", 5 -> "No card", 1 -> "Kings only", 2 -> "Kings only until stock empty", 7 -> "Aces only", 9 -> "Kings or aces only", 8 -> "Sevens only"
    ),
    "P0fo" -> Map(2 -> "Waste", 4 -> "Tableau", 128 -> "Pyramid", 8 -> "Foundation")
  )
}
