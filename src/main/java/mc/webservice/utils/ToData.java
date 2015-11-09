package mc.webservice.utils;


public class ToData {
	public String toAmtReq;
	public String actToAmt;
	public String difOfTo;
	public String loseAllAmt;
	
	public ToData(){
		
	}
	
	public ToData(String toAmtReq, String actToAmt, String difOfTo, String loseAllAmt){
		this.toAmtReq = toAmtReq;
		this.actToAmt = actToAmt;
		this.difOfTo = difOfTo;
		this.loseAllAmt = loseAllAmt;
	}
	
	public String getToAmtReq() {
		return toAmtReq;
	}
	public void setToAmtReq(String toAmtReq) {
		this.toAmtReq = toAmtReq;
	}
	
	public String getActToAmt() {
		return actToAmt;
	}
	public void setActToAmt(String actToAmt) {
		this.actToAmt = actToAmt;
	}
	
	public String getDifOfTo() {
		return difOfTo;
	}
	public void setDifOfTo(String difOfTo) {
		this.difOfTo = difOfTo;
	}

	public String getLoseAllAmt() {
		return loseAllAmt;
	}

	public void setLoseAllAmt(String loseAllAmt) {
		this.loseAllAmt = loseAllAmt;
	}
}
