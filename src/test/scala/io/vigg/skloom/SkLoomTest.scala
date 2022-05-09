package io.vigg.skloom

import io.vigg.skloom._
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import java.util.concurrent.{ExecutorService, Executors}

@RunWith(classOf[JUnit4])
class SkLoomTest {

  @Test def testLoom(): Unit = {

    implicit val executor: ExecutorService =
      Executors.newVirtualThreadPerTaskExecutor()

    val channel1 = new Channel[String] {}
    val channel2 = new Channel[String] {}

    channel1.put("a1")
    channel2.put("a2")

    val selector = new Select[String](
      Seq(
        (
          channel1,
          (s) => {
            assert(s.equals("a1"))
            println(s)
          }
        ),
        (
          channel2,
          (s) => {
            assert(s.equals("a2"))
            println(s)
          }
        )
      )
    )

    go(() => {
      selector.run()
    })

    Thread.sleep(3000)
    selector.interrupt()

  }

}
