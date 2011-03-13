package org.reliant.mpctouch.main

import org.atmosphere.grizzly.AtmosphereSpadeServer

import org.reliant.mpctouch.mpd.{BroadcasterPoller,MpdStatusPoller,Run}

object WebRunner {
  def main(args: Array[String]) {
    val server = AtmosphereSpadeServer.build("http://localhost:8080/mpctouch", "org.reliant.mpctouch")
    MpdStatusPoller.init
    server.start
  }
}

