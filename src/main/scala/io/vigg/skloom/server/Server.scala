package io.vigg.skloom.server

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.eclipse.jetty.server.{ Request, Server, ServerConnector }
import org.eclipse.jetty.server.handler.AbstractHandler

class LoomServer(port: Int) {

  def start(port: Int): Unit = {

    val server = new Server(new VirtualThreadPool)
    val connector = new ServerConnector(server)
    connector.setPort(port)

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

      }})


    server.start()
    server.join()

  }

}
