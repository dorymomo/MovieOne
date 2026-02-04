package com.koreait.dto;

public class joinDTO {
//	MEM_NAME VARCHAR2(100) NOT NULL,
//	 mv_title varchar2(100) NOT NULL,
//	 REV_SHOW_DATE DATE NOT NULL,
//	 mv_location varchar2(100) NOT NULL,
//	REV_REG_DATE DATE NOT NULL,
	
	private int memNum;
	private String mvTitle;
	private String mvLocation;
	private String revRegDate;
	private String revShowDate;
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMvTitle() {
		return mvTitle;
	}
	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}
	public String getMvLocation() {
		return mvLocation;
	}
	public void setMvLocation(String mvLocation) {
		this.mvLocation = mvLocation;
	}
	public String getRevRegDate() {
		return revRegDate;
	}
	public void setRevRegDate(String revRegDate) {
		this.revRegDate = revRegDate;
	}
	public String getRevShowDate() {
		return revShowDate;
	}
	public void setRevShowDate(String revShowDate) {
		this.revShowDate = revShowDate;
	}
	@Override
	public String toString() {
		return "joinDTO [memNum=" + memNum + ", mvTitle=" + mvTitle + ", mvLocation=" + mvLocation + ", revRegDate="
				+ revRegDate + ", revShowDate=" + revShowDate + "]";
	}
	
	
	
	  
	 
	 
	 
}
