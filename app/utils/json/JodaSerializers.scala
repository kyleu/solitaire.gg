package utils.json

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.generic.extras.Configuration
import org.joda.time.LocalDateTime
import utils.DateUtils

object JodaSerializers {
  private[this] implicit val config = Configuration.default.withDefaults

  implicit val localDateTimeFormat: Encoder[LocalDateTime] with Decoder[LocalDateTime] = new Encoder[LocalDateTime] with Decoder[LocalDateTime] {
    override def apply(a: LocalDateTime): Json = Encoder.encodeLong.apply(DateUtils.toMillis(a))
    override def apply(c: HCursor): Result[LocalDateTime] = Decoder.decodeLong.map(s => DateUtils.fromMillis(s)).apply(c)
  }
}
