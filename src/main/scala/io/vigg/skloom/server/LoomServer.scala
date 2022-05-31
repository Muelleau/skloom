package io.vigg.skloom.server

import org.eclipse.jetty.server.{ Request, Server, ServerConnector }
import org.eclipse.jetty.server.handler.AbstractHandler

abstract class LoomServer(port: Int) {

  val handler: AbstractHandler

  def start(port: Int): Unit = {

    val server = new Server(new VirtualThreadPool)
    val connector = new ServerConnector(server)
    connector.setPort(port)

    server.setConnectors(Array(connector))

    server.setHandler(handler)

    server.start()
    server.join()

  }

}
