package io.vigg.skloom

class WaitGroup {

  private var jobs = 0

  def add(i: Int): Unit = {
    jobs += i
  }

  def done(): Unit = {
    if ({ jobs -= 1; jobs } == 0) {
      notifyAll()
    }
  }

  @throws[InterruptedException]
  def await(): Unit = {
    synchronized {
      while ({
        jobs > 0
      }) wait()
    }
  }

}
