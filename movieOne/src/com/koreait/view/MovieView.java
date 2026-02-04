package com.koreait.view;

import java.util.List;
import java.util.Scanner;

import com.koreait.dto.MemberDTO;
import com.koreait.dto.MovieDTO;
import com.koreait.dto.ReservationDTO;

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

	public void inputlogin() {
		
	}
	
	public void inputjoin() {
		
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
	
//	----------------------------------------------------
	public void showMovieListByStream(List<MovieDTO> list) {
		if(list.isEmpty()) {
			System.out.println("현재 상영 가능한 영화가 없습니다!");
		}
		
		list.stream().forEach(x -> {
			System.out.println(x.getMvTitle() + ", " + x.getMvPrice() + ", " + x.getMvGenre() + ", " + x.getMvLocation());
		});
	}
//	----------------------------------------------------
	public void findMovieWithGenre(List<MovieDTO> list) { // 1-2. 영화 장르로 찾기
		if(list.isEmpty()) {
			System.out.println("검색 결과가 없습니다!");
		}
		System.out.println("영화 장르로 찾기");
	}

	public MovieDTO addMovie() { // 1-3. 영화 추가하기
		MovieDTO m = new MovieDTO();
		
		System.out.print("추가할 영화의 제목을 입력해주세요 : ");
		m.setMvTitle(sc.nextLine());
		System.out.print("추가할 영화의 장르를 입력해주세요 : ");
		m.setMvGenre(sc.nextLine());
		System.out.print("추가할 영화의 가격을 입력해주세요 : ");
		m.setMvPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("추가할 영화의 상영관을 입력해주세요 : ");
		m.setMvLocation(sc.nextLine());
		
		return m;
	}

	public String deleteMovie() { // 1-4. 영화 삭제하기
		System.out.println("영화 삭제하기");
		
		System.out.print("삭제할 영화의 제목을 입력해주세요 : ");
		String mvTitle = sc.nextLine();
		
		
		return mvTitle;
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

	public MovieDTO doReservation(List<MovieDTO> list) { // 2-1. 영화 예매하기
		if(list.isEmpty()) {
			System.out.println("예매 가능한 영화가 없습니다");
		}
		MovieDTO reserv = null;
		System.out.println("영화 예매하기");
		
		System.out.println("예매하고 싶은 영화명을 입력해주세요");
		String title = sc.nextLine();
		
		for(MovieDTO m : list) {
			if(m.getMvTitle().equals(title))
			{
				reserv = m;
				break;
			}
		}
		return reserv;	
		}
		
	

	public void myReservation(List<ReservationDTO> list) { // 2-2. 내 예매 내역 확인하기
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		
//		System.out.println("내 예매 내역 조회"); // 일단 나중에 join 완성되면 
//		for (ReservationDTO m : list) {
//			System.out.println(m.get() + ", " + m.getMvPrice() + ", " + m.getMvGenre() + ", " + m.getMvLocation());
//		}
	}

	public void editReservation(List<MovieDTO> list) { // 2-3. 내 예매 수정하기
		if(list.isEmpty()) {
			System.out.println("수정할 예매가 없습니다!");
		}
		System.out.println("내 예매 수정하기");
	}

	public ReservationDTO cancelReservation(List<ReservationDTO> list) { // 2-4. 예매 취소하기
		
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		
		ReservationDTO reserv = null;
	
		System.out.println("예매 취소하기");
		
		System.out.println("취소하고 싶은 예매번호를 입력해주세요");
		int select = sc.nextInt();
		sc.nextLine();
		
		for(ReservationDTO m : list) {
			if(m.getRevNum() == select)
			{
				reserv = m;
				break;
			}
		}
		return reserv;
		
	}

//	-----------------------------------------------------

	public MemberDTO userChange() { // 3. 회원 정보 수정하기
		MemberDTO mem = new MemberDTO();
		
		System.out.println("----회원 정보 수정----");
		System.out.print("변경할 이메일을 입력하세요 : ");
		mem.setMemEmail(sc.nextLine());
		System.out.print("변경할 전화번호를 입력하세요 : ");
		mem.setMemPhoneNo(sc.nextLine());
		
		return mem;
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
	
	public String inputDate() {
		System.out.println("날짜를 입력해주세요.");
		return sc.nextLine();
	}

//	----------------------------------------------------
	
	// 메시지 출력 메소드
	public void msg(String msg) {
		System.out.println(msg);
	}

}
