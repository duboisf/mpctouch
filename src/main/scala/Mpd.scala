package org.reliant.mpctouch.mpd

import grizzled.slf4j.Logging

import java.net.ConnectException

import org.atmosphere.cpr.{Broadcaster,BroadcasterFactory}

import scala.actors.Actor
import scala.actors.Actor._
import scala.collection.JavaConverters._

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

object MpdStatusPoller extends Logging {
  import MpdApi.mpd

  private case class Poll()

  private val poller = actor {
    loop {
      react {
        case Poll => {
          info("Checking idle status...") 
          val statusChange = mpd.idle()
          println("status changed: " + statusChange)
          looper ! Poll
        }
      }
    }
  }

  private val looper = actor {
    loop {
      react {
        case msg => sender ! msg
      }
    }
  }

  def init {
    poller ! Poll
  }
}

private object MpdApi {
  import org.reliant.mpd.Mpd
  val mpd = new Mpd
}

case class Run()

case class StopPolling()

object BroadcasterPoller extends Actor {

  private var run = true

  private object Looper extends Actor {
    start

    def act {
      loop {
        react {
          case msg => {
            Thread sleep 2000
            sender ! msg
          }
        }
      }
    }
  }

  start
  def broadcasters: Iterable[Broadcaster] = BroadcasterFactory.getDefault.lookupAll.asScala

  def act() {
    loop {
      react {
        case Run => {
          println("I see " + broadcasters.size + " broadcasters:")
          broadcasters foreach {x => println("  " + x.getID)}
          if (run) {
            Looper ! Run
          } else {
            run = true
          }
        }
        case Stop => run = false
      }
    }
  }
}
