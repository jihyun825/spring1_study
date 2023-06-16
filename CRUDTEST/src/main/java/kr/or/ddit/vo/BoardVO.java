package kr.or.ddit.vo;

import lombok.Data;

@Data
public class BoardVO {

	private String bono		;
	private String botitle	;		
	private String bocontent;			
	private String bowriter	;	
	private String bodate	;	
	private String bohit	;
	public String getBono() {
		return bono;
	}
	public void setBono(String bono) {
		this.bono = bono;
	}
	public String getBotitle() {
		return botitle;
	}
	public void setBotitle(String botitle) {
		this.botitle = botitle;
	}
	public String getBocontent() {
		return bocontent;
	}
	public void setBocontent(String bocontent) {
		this.bocontent = bocontent;
	}
	public String getBowriter() {
		return bowriter;
	}
	public void setBowriter(String bowriter) {
		this.bowriter = bowriter;
	}
	public String getBodate() {
		return bodate;
	}
	public void setBodate(String bodate) {
		this.bodate = bodate;
	}
	public String getBohit() {
		return bohit;
	}
	public void setBohit(String bohit) {
		this.bohit = bohit;
	}	
	
	
}

