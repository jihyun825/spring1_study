package kr.or.ddit.vo;

import lombok.Data;

@Data
public class MemberVO {
	
		private String mem_no       ;
	    private String mem_id       ;
	    private String mem_pw       ;
	    private String mem_name     ;
	    private String mem_gender   ;
	    private String mem_phone    ;
	    private String mem_email    ;
	    private String mem_agree    ;
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
		public String getMem_id() {
			return mem_id;
		}
		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}
		public String getMem_pw() {
			return mem_pw;
		}
		public void setMem_pw(String mem_pw) {
			this.mem_pw = mem_pw;
		}
		public String getMem_name() {
			return mem_name;
		}
		public void setMem_name(String mem_name) {
			this.mem_name = mem_name;
		}
		public String getMem_gender() {
			return mem_gender;
		}
		public void setMem_gender(String mem_gender) {
			this.mem_gender = mem_gender;
		}
		public String getMem_phone() {
			return mem_phone;
		}
		public void setMem_phone(String mem_phone) {
			this.mem_phone = mem_phone;
		}
		public String getMem_email() {
			return mem_email;
		}
		public void setMem_email(String mem_email) {
			this.mem_email = mem_email;
		}
		public String getMem_agree() {
			return mem_agree;
		}
		public void setMem_agree(String mem_agree) {
			this.mem_agree = mem_agree;
		}
	    
	    
}
