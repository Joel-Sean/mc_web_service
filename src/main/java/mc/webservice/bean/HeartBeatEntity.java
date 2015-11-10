package mc.webservice.bean;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "hb")
public class HeartBeatEntity {
	
	
	private NodeEntity node;
	private TaskDetailEntity taskDetail;
	private AppEntity app;
	
	public NodeEntity getNode() {
		return node;
	}
	public void setNode(NodeEntity node) {
		this.node = node;
	}
	public TaskDetailEntity getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(TaskDetailEntity taskDetail) {
		this.taskDetail = taskDetail;
	}
	public AppEntity getApp() {
		return app;
	}
	public void setApp(AppEntity app) {
		this.app = app;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeartBeatEntity [node=");
		builder.append(node);
		builder.append(", taskDetail=");
		builder.append(taskDetail);
		builder.append(", app=");
		builder.append(app);
		builder.append("]");
		return builder.toString();
	}

}
