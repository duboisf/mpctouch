package org.reliant.mpctouch.rest

import javax.ws.rs.{GET,POST,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/player")
@Produces(Array(APPLICATION_JSON))
class Playback {
  import org.reliant.mpctouch.mpd._

  private val SUCCESS = "{\"success\":true}"

  @GET
  @Path("/play/{pos}")
  def play(@PathParam("pos") pos: Int) = {
    Mpd ! Play(pos)
    SUCCESS
  }

  @POST
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
@Produces(Array(APPLICATION_JSON))
class Status {

}
