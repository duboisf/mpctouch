package org.reliant.mpctouch.mpd

import grizzled.slf4j.Logging
import java.net.ConnectException
import scala.actors.Actor

case class Play(pos: Int)
case class PlayId(id: Int)
case class Next()
case class Prev()
case class Stop()
case class Pause()

case class Status()

object Mpd extends Actor with Logging {
  import MpdApi.mpd
  import MpdApi.mpd.player

  start

  def act() {
    loop {
      react {
        case Play(pos) => {
          info(this + " got play(" + pos + ") msg")
          player play pos
          import org.atmosphere.jersey._
          val b = new JerseyBroadcaster().broadcast("BLAH")
        }
        case PlayId(id) => {
          debug(this + " got playid(" + id + ") msg")
          player playid id
        }
        case Next => {
          info(this + " got next msg")
          player.next
        }
        case Prev => {
          debug(this + " got play msg")
          player.prev
        }
        case Stop => {
          debug(this + " got play msg")
          player.stop
        }
        case Pause => {
          debug(this + " got play msg")
          player.pause
        }
        case Status => {
          debug(this + " got play msg")
          mpd.status
        }
        case other => {
          warn(this + " got unknown msg (" + other + ")")
        }
      }
    }
  }

  override def toString() = "Mpd-Actor"
}

private object MpdApi {
  import org.reliant.mpd.Mpd
  val mpd = new Mpd
}
