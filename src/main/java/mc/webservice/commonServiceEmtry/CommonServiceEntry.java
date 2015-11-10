package mc.webservice.commonServiceEmtry;


import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mc.webservice.bean.AppEntity;
import mc.webservice.bean.HeartBeatEntity;
import mc.webservice.bean.PublishList;
import mc.webservice.business.AppBusiness;
import mc.webservice.iCommonService.ICommonService;



@Path(value = "/common")
public class CommonServiceEntry implements ICommonService{
	
	
	@GET
    @Path("/getPublishInfo")
    @Produces({MediaType.APPLICATION_JSON})
	public PublishList getPublishInfo() {
		// TODO Auto-generated method stub
		
		AppBusiness ab = new AppBusiness();
		
		PublishList pl = new PublishList();
		
		return ab.getPublishInfo(pl);
	}

	@POST
    @Path("/HeartBeat")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public HeartBeatEntity HeartBeat(HeartBeatEntity hb) throws IOException{
		
		AppEntity app = hb.getApp();
		HeartBeatEntity hbe = new HeartBeatEntity();
		
		if(app.getAppName().equals("A") && "1.0".equals(app.getVersion())){
			
			String uuid = hb.getNode().getUuid();
			String node_name = hb.getNode().getNode_name();
			
			return hbe;
		}
		
		return null;
		
		
	}
	
}
