package com.koreait.dto;

public class MemberDTO {
//	CREATE TABLE TBL_MEMBER(
//			   MEM_NUM NUMBER,
//			   MEM_ID VARCHAR2(100) NOT NULL,
//			   MEM_PW VARCHAR2(100) NOT NULL,
//			   MEM_NAME VARCHAR2(100) NOT NULL,
//			   MEM_PHONENO VARCHAR2(100),
//			   MEM_EMAIL VARCHAR2(100) NOT NULL,
//			   CONSTRAINT PK_MEM_NUM PRIMARY KEY(MEM_NUM),
//			   CONSTRAINT UK_MEM_EMAIL UNIQUE (MEM_EMAIL)
//			);

	
	private int memNum;
	private String memId;
	private String memPw;
	private String memName;
	private String memPhoneNo;
	private String memEmail;
	
	
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhoneNo() {
		return memPhoneNo;
	}
	public void setMemPhoneNo(String memPhoneNo) {
		this.memPhoneNo = memPhoneNo;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	
	
	@Override
	public String toString() {
		return "MemberDTO [memNum=" + memNum + ", memId=" + memId + ", memPw=" + memPw + ", memName=" + memName
				+ ", memPhoneNo=" + memPhoneNo + ", memEmail=" + memEmail + "]";
	} 
	
}
