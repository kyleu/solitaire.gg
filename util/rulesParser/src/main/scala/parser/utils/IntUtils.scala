package parser.utils

object IntUtils {
  def parse(s: String) = s match {
    case "0x0020|0x0080" => Some(160)
    case "0x0080|0x0020" => Some(160)
    case "2*13" => Some(26)
    case "13*2" => Some(26)
    case "0|0|0" => Some(0)
    case "1|0|0" => Some(1)
    case "1|2|0" => Some(3)
    case "1|0|8" => Some(9)
    case "2|4" => Some(6)
    case "8|2" => Some(10)
    case "128|64" => Some(192)
    case "1|2|16|64" => Some(83)
    case "BIT_STOCK" => Some(1)
    case "BIT_WASTE" => Some(2)
    case "BIT_TABLEAU" => Some(4)
    case "BIT_RESERVE" => Some(64)
    case "BIT_ANY & ~BIT_TABLEAU" => Some(251)
    case "BIT_ANY & ~BIT_RESERVE" => Some(191)
    case "BIT_STOCK|BIT_CELL" => Some(17)
    case "BIT_TABLEAU|BIT_CELL" => Some(20)
    case "BIT_STOCK|BIT_WASTE" => Some(3)
    case "8191&(~4096)" => Some(4095)
    case "1|64|128|256|512|1024|2048|4096" => Some(8129)
    case _ if s.startsWith("0x") => if (s.contains("|")) {
      None
    } else {
      Some(Integer.parseInt(s.trim().substring(2), 16))
    }
    case _ => None
  }
}
