package io.vigg.skloom.server

import org.eclipse.jetty.util.thread.ThreadPool
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

class VirtualThreadPool() extends ThreadPool {

  import io.vigg.skloom._

  val executorService: ExecutorService = Executors.newVirtualThreadPerTaskExecutor()

  override def join(): Unit = {
    executorService.awaitTermination(Long.MaxValue, TimeUnit.NANOSECONDS)
  }

  override def getThreads: Int = 1
  override def getIdleThreads: Int = 1
  override def isLowOnThreads: Boolean = false

  override def execute(command: Runnable): Unit = go(command)

}
