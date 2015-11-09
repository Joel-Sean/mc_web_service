package mc.webservice.bean;

public class TaskConfigEntity {
	
	
		private int config_id;
		private String task_category;
		private ConfigDetailEntity configDetailEntity;
		
		
		public int getConfig_id() {
			return config_id;
		}
		public void setConfig_id(int config_id) {
			this.config_id = config_id;
		}
		public String getTask_category() {
			return task_category;
		}
		public void setTask_category(String task_category) {
			this.task_category = task_category;
		}
		public ConfigDetailEntity getConfigDetailEntity() {
			return configDetailEntity;
		}
		public void setConfigDetailEntity(ConfigDetailEntity configDetailEntity) {
			this.configDetailEntity = configDetailEntity;
		}
		
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("TaskConfigEntity [config_id=");
			builder.append(config_id);
			builder.append(", task_category=");
			builder.append(task_category);
			builder.append(", configDetailEntity=");
			builder.append(configDetailEntity);
			builder.append("]");
			return builder.toString();
		}
		
}
