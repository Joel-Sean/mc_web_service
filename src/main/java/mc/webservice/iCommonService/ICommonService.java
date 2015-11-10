package mc.webservice.iCommonService;


import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import mc.webservice.bean.HeartBeatEntity;
import mc.webservice.bean.PublishList;


@Path(value = "/common")
public interface ICommonService {
	
	@GET
    @Path("/getPublishInfo")
    @Produces({MediaType.APPLICATION_JSON})
    public PublishList getPublishInfo();

	@POST
    @Path("/HeartBeat/postData")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public String HeartBeat(@PathParam("hb") HeartBeatEntity hb)throws IOException;
	
}
