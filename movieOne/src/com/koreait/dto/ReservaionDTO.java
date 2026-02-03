package com.koreait.dto;

public class ReservaionDTO {
//	--예약테이블
//	CREATE TABLE TBL_RESERVATION(
//	   REV_NUM NUMBER PRIMARY KEY,	정수형
//	   MV_NUM NUMBER NOT NULL,		정수형
//	   MEM_NUM NUMBER NOT NULL,		정수형
//	   REV_REG_DATE DATE NOT NULL,	문자열
//	   REV_SHOW_DATE DATE NOT NULL,	문자열
//	   CONSTRAINT FK_MV_NUM FOREIGN KEY(MV_NUM) REFERENCES TBL_MOVIE (MV_NUM),
//	   CONSTRAINT FK_MEM_NUM FOREIGN KEY(MEM_NUM) REFERENCES TBL_MEMBER(MEM_NUM)
//	);
	private int revNum;
	private int mvNum;
	private int memNum;
	private String revRegDate;
	private String revShowDate;
	
	
	
	public int getRevNum() {
		return revNum;
	}

	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}

	public int getMvNum() {
		return mvNum;
	}

	public void setMvNum(int mvNum) {
		this.mvNum = mvNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
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
		return "ReservaionDTO [revNum=" + revNum + ", mvNum=" + mvNum + ", memNum=" + memNum + ", revRegDate="
				+ revRegDate + ", revShowDate=" + revShowDate + "]";
	}
	
	
}


