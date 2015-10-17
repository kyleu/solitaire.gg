package benchmarking

import org.openjdk.jmh.results.RunResult
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.CommandLineOptions

object BenchmarkRunner {
  def main (args: Array[String]){
    import scala.collection.JavaConversions._

    val opts = new CommandLineOptions(args: _*) // parse command line arguments, and then bend them to your will! ;-)

    val runner = new Runner(opts) // full access to all JMH features, you can also provide a custom output Format here

    val results = runner.run()

    results.foreach { result: RunResult ⇒
      println(s" :: ${result.getAggregatedResult.getPrimaryResult}")
    }
  }
}
