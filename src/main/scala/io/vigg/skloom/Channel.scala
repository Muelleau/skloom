package io.vigg.skloom

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

trait Channel[T] {

  val queue: BlockingQueue[T] = new ArrayBlockingQueue[T](1)

}

trait BufferedChannel[T] {

  val capacity: Int
  val queue: BlockingQueue[T] = new ArrayBlockingQueue[T](capacity)

}
