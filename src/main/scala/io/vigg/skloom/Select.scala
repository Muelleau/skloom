package io.vigg.skloom

class Select[T](channels: List[(Channel[T], T => Any)]) {

  private var stop: Boolean = false

  def interrupt(): Unit = {
    this.stop = true
  }

  def run(): Unit = {
    do {
      var selected = false
      channels.iterator
        .takeWhile(_ => !selected)
        .foreach(channel => {
          val z: T = channel._1.queue.poll()
          if (z != null) {
            selected = true
            channel._2(z)
          }
        })
    } while (!stop)
  }

}
