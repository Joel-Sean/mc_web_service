package mc.webservice.bean;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PublishInfoList")

public class PublishList {
	private String pl;

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}
	
}
