import javax.ws.rs.{GET,PUT,Produces,Path,PathParam,FormParam,WebApplicationException}
import javax.ws.rs.core.{MediaType,UriBuilder}

@Path("/test")
class Test {

  @GET
  @Path("/blah")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def test = "{\"success\":true}"
}
