package org.reliant.mpctouch.main

import org.atmosphere.grizzly.AtmosphereSpadeServer

object WebRunner {
  def main(args: Array[String]) {
    val server = AtmosphereSpadeServer.build("http://localhost:8080/ressources", "org.reliant.mpctouch")
    server.start
  }
}

