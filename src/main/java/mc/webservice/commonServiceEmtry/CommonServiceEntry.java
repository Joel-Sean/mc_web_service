package mc.webservice.commonServiceEmtry;


import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mc.webservice.bean.HeartBeatEntity;
import mc.webservice.bean.PublishList;
import mc.webservice.iCommonService.ICommonService;



@Path(value = "/common")
public class CommonServiceEntry implements ICommonService{
	
	@GET
    @Path("/getPublishInfo")
    @Produces({MediaType.APPLICATION_JSON})
	public PublishList getPublishInfo() {
		// TODO Auto-generated method stub
		PublishList pl = new PublishList();
		pl.setPl("Hello Service");
		return pl;
	}

	@POST
    @Path("/HeartBeat")
    @Produces({MediaType.APPLICATION_JSON})
	public String HeartBeat(HeartBeatEntity hb) throws IOException{
		
		String uuid = hb.getNode().getUuid();
		String node_name = hb.getNode().getNode_name();
		
		return uuid+"_"+node_name;
		
	}
	
}
