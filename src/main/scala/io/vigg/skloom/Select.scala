package io.vigg.skloom

import io.vigg.skloom._

class Select[T](channels: Seq[(Channel[T], T => Unit)]) {

  private var stop: Boolean = false

  def interrupt(): Unit = {
    this.stop = true
  }

  def !(): Unit = {
    this.stop = true
  }

  def run(): Unit = {
    do {
      var selected = false
      channels.iterator
        .takeWhile(_ => !selected)
        .foreach(channel => {
          val z: T = channel._1.poll()
          if (z != null) {
            selected = true
            channel._2(z)
          }
        })
    } while (!stop)
  }

}
