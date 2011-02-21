package org.reliant.mpctouch.mpd

import scala.actors.Actor

case class Play(pos: Int)

object MpdActor extends Actor {
  import MpdApi.mpd.player

  start

  def act() {
    loop {
      react {
        case Play(pos) => player play pos
      }
    }
  }
}

object MpdApi {
  import org.reliant.mpd.Mpd

  val mpd = new Mpd
}
