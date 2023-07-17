import org.apache.beam.sdk.io.TextIO
import org.apache.beam.sdk.options.PipelineOptionsFactory
import org.apache.beam.sdk.testing.TestPipeline
import org.apache.beam.sdk.transforms.DoFn
import org.apache.beam.sdk.transforms.DoFn.OutputReceiver
import org.apache.beam.sdk.transforms.DoFn.ProcessElement
import org.apache.beam.sdk.transforms.ParDo
import org.apache.beam.sdk.values.PCollection
import org.scalatest.FunSuite

class MainTest extends FunSuite {
  test("pipeline") {

    val options = PipelineOptionsFactory.create

    //    val tps     = TestPubsub.fromOptions(options)
    //    val ps      = PubsubIO.readMessages()


    val pipeline = TestPipeline.fromOptions(options)

    val p1 = pipeline.apply("source", TextIO.read().from("inputData"))

    val p2: PCollection[String] = p1.apply(
      "transform",
      ParDo.of(new DoFn[String, String] {
        @ProcessElement
        def processElement(msg: String, outputReceiver: OutputReceiver[String]): Unit = {
          outputReceiver.output(msg)
        }
      })
    )

    p2.apply("sink", ParDo.of(
      new DoFn[String, String] {

      }
    ))

    pipeline.run
  }
}
