package parser.politaire

object ParserDefaults {
  val defaults: Map[String, Any] = Map(
    "C0Nm" -> "Cells", "C0Ns" -> "Cell", "C0d" -> 0, "C0e" -> 0, "C0fx" -> 0, "C0n" -> 0, "C0o" -> 255,

    "F0Nm" -> "Foundation", "F0a" -> 3, "F0ao" -> 255, "F0b" -> 21, "F0cs" -> 0, "F0d" -> 0, "F0i" -> 0, "F0m" -> -1,
    "F0mb" -> 0, "F0n" -> -1, "F0o" -> 255, "F0r" -> 128, "F0s" -> 1, "F0u" -> 0, "F0w" -> true, "Fn" -> 1,

    "Nf" -> 1, "Ni" -> 0, "Nl" -> 0, "Nw" -> 0,

    "P0Nm" -> "Pyramid", "P0type" -> 1, "P0size" -> 7, "P0df" -> 0, "P0ds" -> "++++++", "P0f" -> 5, "P0fo" -> 255, "P0n" -> 1,
    "P0o" -> 255, "P0r" -> 32, "P0s" -> 0, "P0tr" -> 32, "P0ts" -> 0, "P0w" -> 0, "Pn" -> 0,

    "R0Nm" -> "Reserve", "R0af" -> 0, "R0an" -> 1, "R0d" -> 0, "R0dd" -> 0, "R0df" -> 100, "R0n" -> 0, "R0t" -> 1, "RDc" -> 0,
    "RDd" -> 1, "RDdo" -> 4, "RDn" -> 0, "RDp" -> 1, "RDpo" -> 4, "RDs" -> 0, "RDx" -> -1,

    "S0Nm" -> "Stock", "Sn" -> 1,

    "T0Nm" -> "Tableau", "T0af" -> 0, "T0an" -> 1, "T0d" -> 4, "T0db" -> 1, "T0dc" -> 0, "T0dd" -> 0, "T0df" -> 0, "T0ds" -> "",
    "T0f" -> 0, "T0fo" -> 255, "T0fx" -> 0, "T0m" -> 0, "T0n" -> 8, "T0o" -> 255, "T0r" -> 32, "T0s" -> 1, "T0sc" -> true,
    "T0tr" -> 32, "T0ts" -> 0, "T0w" -> 0, "Tn" -> 1,

    "W0Nm" -> "Waste", "W0a" -> 0, "W0n" -> 1, "W0s" -> 0,

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
    "reproj" -> 3,
    "rightfunc" -> 2,
    "shuftype" -> 0,
    "smode" -> 1,
    "statusbar" -> 1,
    "stdsuits" -> 15,
    "suits" -> 0,
    "supermoves" -> 0,
    "title" -> "Web Solitaire",
    "toptobot" -> true,
    "touchfunc" -> 0,
    "victory" -> 0,
    "vrank" -> 32,
    "vsuit" -> 1,
    "wrapdeal" -> 0
  )

  def getDefault(key: String) = {
    val c = key(1)
    if (c == '1' || c == '2' || c == '3' || c == '4') {
      defaults.get(key.head + "0" + key.tail.tail)
    } else {
      defaults.get(key)
    }
  }
}
