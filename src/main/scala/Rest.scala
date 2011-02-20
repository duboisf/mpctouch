package org.reliant.mpctouch.rest

import javax.ws.rs.{GET,POST,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/mpd")
@Produces(Array(APPLICATION_JSON))
class Playback {
  import org.reliant.mpctouch.mpd.MpdApi.mpd.player

  private val SUCCESS = "{\"success\":true}"

  @POST
  @Path("/play/{pos}")
  def play = {
    player play 0
    SUCCESS
  }

  @POST
  @Path("/playid/{id}")
  def playid(@PathParam("id") id: Int) = {
    player playid id
    SUCCESS
  }

  @PUT
  @Path("/stop")
  def stop = {
    player.stop
    SUCCESS
  }

  @POST
  @Path("/next")
  def next = {
    player.next
    SUCCESS
  }

  @POST
  @Path("/prev")
  def previous = {
    player.prev
    SUCCESS
  }
}
