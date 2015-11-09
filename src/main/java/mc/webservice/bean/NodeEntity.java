package mc.webservice.bean;

public class NodeEntity {
	
	private String uuid;
	private int node_id;
	private String node_name;
	private String node_description;
	private String node_status;
	private String node_hb_time;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getNode_id() {
		return node_id;
	}

	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	
	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getNode_description() {
		return node_description;
	}

	public void setNode_description(String node_description) {
		this.node_description = node_description;
	}

	public String getNode_status() {
		return node_status;
	}

	public void setNode_status(String node_status) {
		this.node_status = node_status;
	}

	public String getNode_hb_time() {
		return node_hb_time;
	}

	public void setNode_hb_time(String node_hb_time) {
		this.node_hb_time = node_hb_time;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NodeEntity [uuid=");
		builder.append(uuid);
		builder.append(", node_id=");
		builder.append(node_id);
		builder.append(", node_name=");
		builder.append(node_name);
		builder.append(", node_description=");
		builder.append(node_description);
		builder.append(", node_status=");
		builder.append(node_status);
		builder.append(", node_hb_time=");
		builder.append(node_hb_time);
		builder.append("]");
		return builder.toString();
	}
}
