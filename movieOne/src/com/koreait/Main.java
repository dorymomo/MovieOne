package com.koreait;

import com.koreait.controller.MovieController;
/**
 * @author 창훈
 * @since jdk 17
 * <p>메인 메서드를 담당할 메인 클래스입니다.</p>
 */
//역할 분담 
//영화 dto : 성민   회원 가입, 로그인   - 창훈 회원탈퇴, 회원수정
//회원 dto : 해준   예매 조회, 예매    - 창표 예매취소, 예매수정
//예매 dto : 태우   장르 조회, 영화 추가 - 준승  영화조회, 영화삭제    
//view : 준승
//controller : 창표
//Main : 창훈(스트림API)

public class Main
{
	public static void main(String[] args)
	{
		new MovieController().run();
	}
}
