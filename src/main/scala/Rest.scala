package org.reliant.mpctouch.rest

import org.atmosphere.annotation.{Broadcast,Suspend}
import org.atmosphere.cpr.{BroadcasterFactory,DefaultBroadcaster}
import org.atmosphere.jersey.{Broadcastable,JerseyBroadcaster}

import org.reliant.mpctouch.mpd.{BroadcasterPoller,Run,Stop}

import javax.ws.rs.{GET,POST,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Context

@Path("/player")
@Produces(Array("application/json"))
class Playback {
  import org.reliant.mpctouch.mpd._

  private val SUCCESS = "{\"success\":true}"

  @GET
  @Path("/play/{pos}")
  def play(@PathParam("pos") pos: Int) = {
    Mpd ! Play(pos)
    SUCCESS
  }

  @GET
  @Path("/playid/{id}")
  def playid(@PathParam("id") id: Int) = {
    Mpd ! PlayId(id)
    SUCCESS
  }

  @GET
  @Path("/stop")
  def stop = {
    Mpd ! Stop
    SUCCESS
  }

  @GET
  @Path("/next")
  def next = {
    Mpd ! Next
    SUCCESS
  }

  @GET
  @Path("/prev")
  def previous = {
    Mpd ! Prev
    SUCCESS
  }

  @GET
  @Broadcast
  @Path("/test")
  def test = {
    "{'success': 'true'}"
  }
}

@Path("/status")
class Status {

  @GET
  @Suspend(resumeOnBroadcast=true)
  def status = {
    val b = new DefaultBroadcaster("status")
    new Broadcastable("success", b)
  }

  @GET
  @Path("/run")
  def run = {
    BroadcasterPoller ! Run
    "success"
  }

  @GET
  @Path("/stop")
  def stop = {
    BroadcasterPoller ! Stop
    "success"
  }

  @GET
  @Path("/{topic}")
  def test(@PathParam("topic") topic: String) =
    new Broadcastable(topic, new DefaultBroadcaster(topic))
}
