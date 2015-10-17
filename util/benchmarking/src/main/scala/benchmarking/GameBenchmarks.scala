package benchmarking

import org.openjdk.jmh.annotations

class GameBenchmarks {
  @annotations.Benchmark
  def games() = {
    val factory = true
  }
}
