package mc.webservice.bean;

public class IpUvConfigDetailEntity implements ConfigDetailEntity{

	private int ip_number;
	private int uv_number;
	private int pv_number;
	
	
	public int getIp_number() {
		return ip_number;
	}
	public void setIp_number(int ip_number) {
		this.ip_number = ip_number;
	}
	public int getUv_number() {
		return uv_number;
	}
	public void setUv_number(int uv_number) {
		this.uv_number = uv_number;
	}
	public int getPv_number() {
		return pv_number;
	}
	public void setPv_number(int pv_number) {
		this.pv_number = pv_number;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IpUvConfigDetailEntity [ip_number=");
		builder.append(ip_number);
		builder.append(", uv_number=");
		builder.append(uv_number);
		builder.append(", pv_number=");
		builder.append(pv_number);
		builder.append("]");
		return builder.toString();
	}
		
}
