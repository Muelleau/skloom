package io.vigg.skloom

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

trait Channel[T] {

  val queue: BlockingQueue[T] = new ArrayBlockingQueue[T](1)

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

trait BufferedChannel[T] {

  val capacity: Int
  val queue: BlockingQueue[T] = new ArrayBlockingQueue[T](capacity)

}
