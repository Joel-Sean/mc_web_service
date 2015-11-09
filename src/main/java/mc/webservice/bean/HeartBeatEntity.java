package mc.webservice.bean;

public class HeartBeatEntity {
	
	
	private NodeEntity node;
	private TaskDetailEntity taskDetail;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HeartBeatEntity [node=");
		builder.append(node);
		builder.append(", taskDetail=");
		builder.append(taskDetail);
		builder.append("]");
		return builder.toString();
	}

}
