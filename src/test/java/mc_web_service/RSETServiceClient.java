package mc_web_service;

import javax.ws.rs.core.MediaType;

import mc.webservice.bean.AppEntity;
import mc.webservice.bean.HeartBeatEntity;
import mc.webservice.bean.NodeEntity;
import mc.webservice.bean.TaskDetailEntity;
import mc.webservice.utils.SpringBeanFactory;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.interceptor.annotations.Before;

public class RSETServiceClient {
private static WebClient client;
    
    @Before
    public void init() {
       
        ApplicationContext ctx = SpringBeanFactory.getContext();
        client = ctx.getBean("webClient", WebClient.class);
    }
    
    
    @Test
    public void testGet() {
        System.out.println(client.path("getPublishInfo").accept(MediaType.TEXT_PLAIN).get(String.class));
    }
    
    @Test
    public void testPostData() {

    	HeartBeatEntity hbe = new HeartBeatEntity();
    	NodeEntity node = new NodeEntity();
    	TaskDetailEntity taskDetail = new TaskDetailEntity();
    	AppEntity app = new AppEntity();
    	
    	node.setNode_id(1);
    	node.setNode_name("testNode 1");
    	node.setUuid("Joel");
    	node.setNode_status("pending");
    	node.setNode_ip("127.0.0.1");
    	
    	app.setAppName("A");
    	app.setVersion(1.0);
    	
    	hbe.setNode(node);
    	hbe.setTaskDetail(taskDetail);
    	
        System.out.println(client.path("HeartBeat/postData").accept(MediaType.APPLICATION_XML).post(hbe, HeartBeatEntity.class));
    }
}
