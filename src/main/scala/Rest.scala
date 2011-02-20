package org.reliant.mpctouch.rest

import javax.ws.rs.{GET,POST,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.{MediaType,UriBuilder}

@Path("/mpd")
@Produces(Array(MediaType.APPLICATION_JSON))
class Playback {
  import org.reliant.mpctouch.mpd.MpdApi.mpd.player

  private val success = "{\"success\":true}"

  @POST
  @Path("/play/{pos}")
  def play = {
    player play 0
    success
  }

  @GET
  @Path("/playid/{id}")
  def playid(@PathParam("id") id: Int) = {
    player playid id
    success
  }

  @GET
  @Path("/stop")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def stop = {
    player.stop
    success
  }

  @POST
  @Path("/next")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def next = {
    player.next
    success
  }

  @POST
  @Path("/prev")
  def previous = {
    player.prev
    success
  }
}
