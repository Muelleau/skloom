package io.vigg.skloom

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

abstract class Channel[T](capacity: Int) {

  val queue: BlockingQueue[T] = new ArrayBlockingQueue[T](capacity)

  def put(x: T): Unit = {
    queue.put(x)
  }

  def poll(): T = {
    queue.poll()
  }

  def >(x: T): Unit = {
    queue.put(x)
  }

  def <(): T = {
    queue.poll()
  }

}
