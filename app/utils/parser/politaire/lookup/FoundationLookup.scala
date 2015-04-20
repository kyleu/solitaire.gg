package utils.parser.politaire.lookup

import utils.parser.politaire.PolitaireParserHelper

object FoundationLookup {
  val titles = Seq(
    "F0Nm" -> "Foundation name", "F0n" -> "Number of foundation piles", "F0b" -> "Foundation low rank", "F0d" -> "Foundation initial cards",
    "F0s" -> "Foundation suit match rule", "F0r" -> "Foundation rank match rule", "F0w" -> "Foundation wraps from king to ace",
    "F0cs" -> "Foundation add complete sequences only", "F0m" -> "Maximum cards for foundation", "F0mb" -> "Can move cards from foundation",
    "F0o" -> "May move to foundation from", "F0i" -> "Keep foundation off-screen", "F0a" -> "Auto-move cards to foundation",
    "F0ao" -> "Perform auto-moves for foundation from",

    "F1Nm" -> "Foundation B name", "F1n" -> "Number of foundation B piles", "F1b" -> "Foundation B low rank", "F1d" -> "Foundation B initial cards",
    "F1s" -> "Foundation B suit match rule", "F1r" -> "Foundation B rank match rule", "F1w" -> "Foundation B wraps from king to ace",
    "F1cs" -> "Foundation B add complete sequences only", "F1m" -> "Maximum cards for foundation B", "F1mb" -> "Can move cards from foundation B",
    "F1o" -> "May move to foundation B from", "F1i" -> "Keep foundation B off-screen", "F1a" -> "Auto-move cards to foundation B",
    "F1ao" -> "Perform auto-moves for foundation B from",

    "F2Nm" -> "Foundation C name", "F2n" -> "Number of foundation C piles", "F2b" -> "Foundation C low rank", "F2d" -> "Foundation C initial cards",
    "F2s" -> "Foundation C suit match rule", "F2r" -> "Foundation C rank match rule", "F2w" -> "Foundation C wraps from king to ace",
    "F2cs" -> "Foundation C add complete sequences only", "F2m" -> "Maximum cards for foundation C", "F2mb" -> "Can move cards from foundation C",
    "F2o" -> "May move to foundation C from", "F2i" -> "Keep foundation C off-screen", "F2a" -> "Auto-move cards to foundation C",
    "F2ao" -> "Perform auto-moves for foundation C from",

    "F3Nm" -> "Foundation D name", "F3n" -> "Number of foundation D piles", "F3b" -> "Foundation D low rank", "F3d" -> "Foundation D initial cards",
    "F3s" -> "Foundation D suit match rule", "F3r" -> "Foundation D rank match rule", "F3w" -> "Foundation D wraps from king to ace",
    "F3cs" -> "Foundation D add complete sequences only", "F3m" -> "Maximum cards for foundation D", "F3mb" -> "Can move cards from foundation D",
    "F3o" -> "May move to foundation D from", "F3i" -> "Keep foundation D off-screen", "F3a" -> "Auto-move cards to foundation D",
    "F3ao" -> "Perform auto-moves for foundation D from"
  )
  val translations = Map(
    "F0n" -> Map(-1 -> "4 per deck", 1 -> "1 stack", 2 -> "2 stacks", 4 -> "4 stacks", 8 -> "8 stacks", 12 -> "12 stacks", 16 -> "16 stacks"),
    "F0b" -> Map(
      21 -> "Deck's low card", 22 -> "Deck's high card",
      1 -> "A", 2 -> "2", 3 -> "3", 4 -> "4", 5 -> "5", 6 -> "6", 7 -> "7", 8 -> "8", 9 -> "9", 10 -> "X",
      11 -> "J", 12 -> "Q", 23 -> "Ace, Two, Thre...", 20 -> "Any Card"
    ),
    "F0d" -> Map(0 -> "None", 1 -> "1 cards", 2 -> "2 cards", 3 -> "3 cards", 4 -> "4 cards"),
    "F0s" -> Map(1 -> "In same suit", 2 -> "In different suits", 3 -> "In same color", 4 -> "In alternating colors", 5 -> "Regardless of suit"),
    "F0r" -> Map(
      128 -> "Build up", 32 -> "Build down", 64 -> "Build equal", 160 -> "Build up or down", 96 -> "Build up, down, or equal",
      256 -> "Build up by 2", 16 -> "Build down by 2", 512 -> "Build up by 3", 8 -> "Build down by 3", 1024 -> "Build up by 4",
      4 -> "Build down by 4", 8192 -> "Build nth pile up by n", 8191 -> "Regardless of rank"
    ),
    "FOw" -> Map(0 -> "No", 1 -> "Yes"),
    "F0cs" -> Map(0 -> "No", 1 -> "Yes"),
    "F0mb" -> Map(0 -> "Never", 1 -> "Always", 2 -> "Once the stock is empty"),
    "F0o" -> PolitaireParserHelper.pileTypes,
    "F0i" -> Map(0 -> "No", 1 -> "Yes"),
    "F0a" -> Map(
      0 -> "Never", 4 -> "Keeping piles level", 3 -> "When all stackable cards are off", 2 -> "When one stackable card is off",
      5 -> "When stackable cards are removable", 1 -> "Whenever possible"
    ),
    "F0ao" -> PolitaireParserHelper.pileTypes
  )
}
