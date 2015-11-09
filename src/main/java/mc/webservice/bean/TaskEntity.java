package mc.webservice.bean;

public class TaskEntity {

		private int task_id;
		private String task_name;
		private String task_description;
		private MemberEntity memberEntity;
		
		public int getTask_id() {
			return task_id;
		}
		public void setTask_id(int task_id) {
			this.task_id = task_id;
		}
		public String getTask_name() {
			return task_name;
		}
		public void setTask_name(String task_name) {
			this.task_name = task_name;
		}
		public String getTask_description() {
			return task_description;
		}
		public void setTask_description(String task_description) {
			this.task_description = task_description;
		}
		public MemberEntity getMemberEntity() {
			return memberEntity;
		}
		public void setMemberEntity(MemberEntity memberEntity) {
			this.memberEntity = memberEntity;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TaskEntity [task_id=");
			builder.append(task_id);
			builder.append(", task_name=");
			builder.append(task_name);
			builder.append(", task_description=");
			builder.append(task_description);
			builder.append(", memberEntity=");
			builder.append(memberEntity);
			builder.append("]");
			return builder.toString();
		}
		

		
		
}
