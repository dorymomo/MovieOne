package com.koreait.view;

import java.util.List;
import java.util.Scanner;

import com.koreait.dto.MovieDTO;

public class MovieView {


	private Scanner sc = new Scanner(System.in);

//	영화 dto : 성민 회원 가입, 로그인 - 창훈 회원탈퇴, 회원수정 
//	회원 dto : 해준 예매 조회, 예매 - 창표 예매취소, 예매수정 
//	예매 dto : 태우 장르 조회, 영화 추가 - 준승 영화조회, 영화삭제

	public int loginMenu() {
		System.out.println("안녕하세요!");
		System.out.println("1. 로그인하기");
		System.out.println("2. 회원가입하기");

		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

//	----------------------------------------------
	
	public int mainMenu() {
		System.out.println("----메뉴선택----");
		System.out.println("1. 영화");
		System.out.println("2. 예매");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원탈퇴");
		System.out.println("0. 종료");
		System.out.println("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

//	-----------------------------------------------
	
	public int movieMenu() { // 1. 영화 메뉴
		System.out.println("----영화 메뉴----");
		System.out.println("1. 전체 영화 보기");
		System.out.println("2. 영화 장르로 찾기");
		System.out.println("3. 영화 추가");
		System.out.println("4. 영화 삭제");
		System.out.println("0. 종료");

		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	public void showMovieList(List<MovieDTO> list) { // 1-1. 전체 영화 보기
		if (list.isEmpty()) { // 매개변수로 받은 list 원소가 비어있는지 검사
			System.out.println("현재 상영 가능한 영화가 없습니다!");
		}
		for (MovieDTO m : list) { // list 안에 원소가 있다면 for-each문을 통해 저장된 값 출력
			System.out.println(
					m.getMvTitle() + " , " + m.getMvPrice() + " , " + m.getMvGenre() + " , " + m.getMvLocation());

		}
	}

	public void findMovieWithGenre(List<MovieDTO> list) { // 1-2. 영화 장르로 찾기
		if(list.isEmpty()) {
			System.out.println("검색 결과가 없습니다!");
		}
		System.out.println("영화 장르로 찾기");
	}

	public void addMovie(String newMv) { // 1-3. 영화 추가하기
//		if() > 이미 영화 리스트에 있으면 추가 못하는거
		System.out.println("영화 추가하기");
	}

	public void deleteMovie(String delMv) { // 1-4. 영화 삭제하기
//		if() > 리스트에 없는 영화면 삭제 불가
		System.out.println("영화 삭제하기");
	}

//	-------------------------------------

	public int showResvMenu() { // 2. 예매 메뉴
		System.out.println("----예매 메뉴----");
		System.out.println("1. 예매하기");
		System.out.println("2. 내 예매 내역");
		System.out.println("3. 예매 수정");
		System.out.println("4. 예매 취소");
		System.out.println("0. 종료");

		int choice = sc.nextInt();
		sc.nextLine();
		return choice;

	}

	public void doReservation(List<MovieDTO> list) { // 2-1. 영화 예매하기
		if(list.isEmpty()) {
			System.out.println("예매 가능한 영화가 없습니다!");
		}
		System.out.println("영화 예매하기");
	}

	public void myReservation(List<MovieDTO> list) { // 2-2. 내 예매 내역 확인하기
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		System.out.println("내 예매 내역 조회");
	}

	public void editReservation(List<MovieDTO> list) { // 2-3. 내 예매 수정하기
		if(list.isEmpty()) {
			System.out.println("수정할 예매가 없습니다!");
		}
		System.out.println("내 예매 수정하기");
	}

	public void cancelReservation(List<MovieDTO> list) { // 2-4. 예매 취소하기
		if(list.isEmpty()) {
			System.out.println("취소할 예매가 없습니다!");
		}
		System.out.println("예매 취소하기");
	}

//	-----------------------------------------------------

	public void userChange() { // 3. 회원 정보 수정하기
		System.out.println("----회원 정보 수정----");
		System.out.print("변경할 이메일을 입력하세요 : ");
		sc.nextLine();
		System.out.print("변경할 전화번호를 입력하세요 : ");
		sc.nextLine();
	}

//	----------------------------------------------------
	
	public int deleteAccount() { // 4. 회원 탈퇴하기

		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1. 네");
		System.out.println("2. 아니오");

		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

//	----------------------------------------------------
	
	// 메시지 출력 메소드
	public void msg(String msg) {
		System.out.println(msg);
	}

}
