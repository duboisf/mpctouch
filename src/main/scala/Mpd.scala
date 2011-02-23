package org.reliant.mpctouch.mpd

import java.net.ConnectException
import scala.actors.Actor

case class Play(pos: Int)
case class PlayId(id: Int)
case class Next()
case class Prev()
case class Stop()
case class Pause()

case class Status()

object Mpd extends Actor {
  import MpdApi.mpd
  import MpdApi.mpd.player

  start

  def act() {
    loop {
      react {
        case Play(pos) => player play pos
        case PlayId(id) => player playid id
        case Next => player.next
        case Prev => player.prev
        case Stop => player.stop
        case Pause => player.pause
        case Status => mpd.status
      }
    }
  }
}

private object MpdApi {
  import org.reliant.mpd.Mpd
  val mpd = new Mpd
}
