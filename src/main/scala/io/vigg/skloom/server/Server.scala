package io.vigg.skloom.server

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.eclipse.jetty.server.{ Request, Server, ServerConnector }
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.util.thread.ThreadPool
import java.util.concurrent.TimeUnit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


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

class LoomServer(port: Int) {

  def start(): Unit = {

    val server = new Server(new VirtualThreadPool)
    val connector = new ServerConnector(server)
    connector.setPort(8090)

    server.setConnectors(Array(connector))

    server.setHandler(new AbstractHandler {
      override def handle(
       target: String,
       baseRequest: Request,
       request: HttpServletRequest,
       response: HttpServletResponse
      ): Unit = {
        val out = response.getWriter
        out.println("<h1>" + "hello" + "</h1>")

        baseRequest.setHandled(true)
        println("handling request")
      }})


    server.start()
    server.join()

  }

}


object S {
  def main(args: Array[String]): Unit = {
    val loomServer = new LoomServer(8082)
    loomServer.start()
  }

}
