package mc.webservice.bean;

public class TaskDetailEntity {
	
	private TaskEntity taskEntity;
	private TaskConfigEntity taskConfigEntity;

	private String task_status;
	private String order_quantity;
	private String order_executed;
	
	
	public TaskEntity getTaskEntity() {
		return taskEntity;
	}
	public void setTaskEntity(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}
	public TaskConfigEntity getTaskConfigEntity() {
		return taskConfigEntity;
	}
	public void setTaskConfigEntity(TaskConfigEntity taskConfigEntity) {
		this.taskConfigEntity = taskConfigEntity;
	}
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	public String getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(String order_quantity) {
		this.order_quantity = order_quantity;
	}
	public String getOrder_executed() {
		return order_executed;
	}
	public void setOrder_executed(String order_executed) {
		this.order_executed = order_executed;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaskDetailEntity [taskEntity=");
		builder.append(taskEntity);
		builder.append(", taskConfigEntity=");
		builder.append(taskConfigEntity);
		builder.append(", task_status=");
		builder.append(task_status);
		builder.append(", order_quantity=");
		builder.append(order_quantity);
		builder.append(", order_executed=");
		builder.append(order_executed);
		builder.append("]");
		return builder.toString();
	}
	
	
}
