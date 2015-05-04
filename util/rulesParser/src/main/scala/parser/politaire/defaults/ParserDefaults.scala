package parser.politaire.defaults

object ParserDefaults {
  val defaults: Map[String, Any] = {
    CellDefaults.defaults ++
    FoundationDefaults.defaults ++
    PyramidDefaults.defaults ++
    ReserveDefaults.defaults ++
    TableauDefaults.defaults ++
    WasteDefaults.defaults ++
    Map(
      "Nf" -> 1, "Ni" -> 0, "Nl" -> 0, "Nw" -> 0,

      "S0Nm" -> "Stock", "Sn" -> 1, "S0cardsShown" -> 1,
      "Z0Nm" -> "Pocket",

      "accord" -> 5,
      "ashuf" -> 8,
      "dealchunk" -> 1,
      "dealto" -> 1,
      "desc" -> "Yet another solitaire game.",
      "drawrule" -> 0,
      "gallery" -> 0,
      "leftfunc" -> 0,
      "like" -> "",
      "lowpip" -> 1,
      "maxdeals" -> 1,
      "midfunc" -> 3,
      "millres" -> 0,
      "ndecks" -> 1,
      "ndraw" -> 0,
      "newfunc" -> 0,
      "nrot" -> 0,
      "pairs" -> 0,
      "ranks" -> 8191,
      "related" -> "",
      "reproj" -> 3,
      "rightfunc" -> 2,
      "shuftype" -> 0,
      "smode" -> 1,
      "statusbar" -> 1,
      "stdsuits" -> 15,
      "suits" -> 0,
      "supermoves" -> 0,
      "title" -> "Scalataire",
      "toptobot" -> true,
      "touchfunc" -> 0,
      "victory" -> 0,
      "vrank" -> 32,
      "vsuit" -> 1,
      "wrapdeal" -> 0
    )
  }

  def getDefault(key: String) = {
    val c = key(1)
    if (c == '1' || c == '2' || c == '3' || c == '4') {
      defaults.get(key.head + "0" + key.tail.tail)
    } else {
      defaults.get(key)
    }
  }
}
