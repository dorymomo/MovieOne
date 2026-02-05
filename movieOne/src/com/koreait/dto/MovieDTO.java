package com.koreait.dto;

public class MovieDTO {
	/**
	 * @author 김성민
	 * @version jdk17 
	 * 
	 * 영화의 영화 정보를 담는 DTO 클래스이다. 
	 * 영화 번호 , 영화제목 , 영화 장르, 영화 가격,영화 장소를 관리한다 
	 * (영화 조회/삭제/추가 장르 조회)
	 */
	
//CREATE TABLE tbl_movie(
//	   mv_num NUMBER,
//	   mv_title varchar2(100) NOT NULL,
//	   mv_genre varchar2(100) NOT NULL,
//	   mv_price NUMBER NOT NULL,
//	   mv_location varchar2(100) NOT NULL,
//	   CONSTRAINT pk_movie_num PRIMARY key(mv_num),
//	   CONSTRAINT uk_movie_title unique(mv_title)
	//동일하게 관리 할 필드를 선언했습니다.
	private int mvNum;
	private String mvTitle;
	private String mvGenre;
	private int mvPrice;
	private String mvLocation;

	
	
	public int getMvNum() {
		return mvNum;
	}
	public void setMvNum(int mvNum) {
		this.mvNum = mvNum;
	}
	public String getMvTitle() {
		return mvTitle;
	}
	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}
	public String getMvGenre() {
		return mvGenre;
	}
	public void setMvGenre(String mvGenre) {
		this.mvGenre = mvGenre;
	}
	public int getMvPrice() {
		return mvPrice;
	}
	public void setMvPrice(int mvPrice) {
		this.mvPrice = mvPrice;
	}
	public String getMvLocation() {
		return mvLocation;
	}
	public void setMvLocation(String mvLocation) {
		this.mvLocation = mvLocation;
	}
	
	
	@Override
	public String toString() {
		return "MovieDTO [mvNum=" + mvNum + ", mvTitle=" + mvTitle + ", mvGenre=" + mvGenre + ", mvPrice=" + mvPrice
				+ ", mvLocation=" + mvLocation + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
