package com.koreait.dto;
/**
 * @author 이해준
 * @version jdk17 
 * 
 * 예매를 조회하기 위한 클래스 
 * 회원의 이름, 영화제목 예약일, 상영관, 상영일 정보를 관리한다
 * 
 */
public class JoinDTO {
	
	/**
	 * 영화 예메를 위한 joinDTO이다. 
	 * 영화,예매,회원 테이블을 사용하였다 
	 */
//	 MEM_NAME VARCHAR2(100) NOT NULL,
//	 mv_title varchar2(100) NOT NULL,
//	 REV_SHOW_DATE DATE NOT NULL,
//	 mv_location varchar2(100) NOT NULL,
//	 REV_REG_DATE DATE NOT NULL,
	
	/**
	 * 필드
	 */
	private int memNum;
	private String mvTitle;
	private String mvLocation;
	private String revRegDate;
	private String revShowDate;
	private int revNum;
	
	
	
	/**
	 * getter,setter 생성 
	 * 
	 */
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
	public int getRevNum() {
		return revNum;
	}
	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	
	/**
	 * 객체의 필드 정보를 문자열 형태로 반환한다 
	 * @return 회원 정보 문자열 
	 */
	@Override
	public String toString() {
		return "joinDTO [memNum=" + memNum + ", mvTitle=" + mvTitle + ", mvLocation=" + mvLocation + ", revRegDate="
				+ revRegDate + ", revShowDate=" + revShowDate + ", revNum=" + revNum + "]";
	}
	
	
	
	
	  
	 
	 
	 
}
