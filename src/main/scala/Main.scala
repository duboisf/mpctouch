package org.reliant.mpctouch.main

import com.sun.jersey.spi.container.servlet.ServletContainer
import com.sun.jersey.api.core.PackagesResourceConfig.PROPERTY_PACKAGES
import org.eclipse.jetty.server.Server

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import javax.servlet.ServletException

import java.io.IOException

import org.eclipse.jetty.server.{Server, Request}
import org.eclipse.jetty.server.handler.AbstractHandler
import org.eclipse.jetty.server.nio.SelectChannelConnector
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}

import javax.ws.rs.{GET, Produces, Path}


object WebRunner {
  def main(args: Array[String]) {

    val server = new Server(8080)
    val connector = new SelectChannelConnector()
    server.addConnector(connector)

    val holder:ServletHolder = new ServletHolder(classOf[ServletContainer])
    holder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                            "com.sun.jersey.api.core.PackagesResourceConfig")
    holder.setInitParameter(PROPERTY_PACKAGES, "marcus.test")
    val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
    context.addServlet(holder, "/*")
    server.start
    server.join
  }
}


@Path("/helloworld")
class TestResource {
  @GET
  def hello() = "HELLO!!"
}

