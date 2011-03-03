package org.reliant.mpctouch.rest

import org.atmosphere.annotation.Suspend
import javax.ws.rs.{GET,POST,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.MediaType

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

  @PUT
  @Path("/stop")
  def stop = {
    Mpd ! Stop
    SUCCESS
  }

  @POST
  @Path("/next")
  def next = {
    Mpd ! Next
    SUCCESS
  }

  @POST
  @Path("/prev")
  def previous = {
    Mpd ! Prev
    SUCCESS
  }
}

@Path("/status")
@Produces(Array("application/json"))
class Status {
  
  @GET
  @Suspend(resumeOnBroadcast=true)
  def status() = "{\"success\": true}"
  
}
