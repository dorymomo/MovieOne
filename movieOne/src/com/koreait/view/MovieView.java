package com.koreait.view;

import java.util.List;
import java.util.Scanner;

import com.koreait.dto.MemberDTO;
import com.koreait.dto.MovieDTO;
import com.koreait.dto.JoinDTO;

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

	public String inputId() {
		System.out.println("아이디를 입력해주세요! : ");
		String userId = sc.nextLine();
		
		return userId;
		
	}
	
	public String inputPw() {
		System.out.println("비밀번호를 입력해주세요 ! : ");
		String userPw = sc.nextLine();
		
		return userPw;
	}
	
	public void inputjoin(MemberDTO m) {
		System.out.println("회원가입을 진행합니다");
		
		System.out.println("사용할 아이디를 입력해주세요 : ");
		m.setMemId(sc.nextLine());
		System.out.println("사용할 비밀번호를 입력해주세요 : ");
		m.setMemPw(sc.nextLine());
		System.out.println("이름을 입력해주세요 : ");
		m.setMemName(sc.nextLine());
		System.out.println("전화번호를 입력해주세요 : ");
		m.setMemPhoneNo(sc.nextLine());
		System.out.println("이메일을 입력해주세요 : ");
		m.setMemEmail(sc.nextLine());
		
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
	/**
	 * @author 창훈
	 * @param MovieDTO List
	 * <p> movieDTOList를 받아 상영가능한 영화를 출력하는메소드를 stream으로 작업한 메소드입니다.</p>
	 */
	public void showMovieListByStream(List<MovieDTO> list) {
		if(list.isEmpty()) {
			System.out.println("현재 상영 가능한 영화가 없습니다!");
		}
		
		list.stream().forEach(x -> {
			System.out.println(x.getMvTitle() + ", " + x.getMvPrice() + ", " + x.getMvGenre() + ", " + x.getMvLocation());
		});
	}
//	----------------------------------------------------
	public String findMovieWithGenre() { // 1-2. 영화 장르로 찾기
		
		System.out.println("영화 장르로 찾기");
		System.out.println("찾고 싶은 장르를 입력해주세요 : ");
		String t = sc.nextLine();
	
		return t;
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
	
	
	
	
	
	public MovieDTO movieSelect(List<MovieDTO> list) { // 2-1. 영화 예매하기
		
		
		if(list.isEmpty()) {
			System.out.println("예매 가능한 영화가 없습니다");
		}
		
		MovieDTO reserv = null;
		
		System.out.println("영화 예매하기");
		System.out.println("현재 상영 가능한 영화");
		
		for (MovieDTO m : list) {
			System.out.println(m.getMvTitle() + ", " + m.getMvPrice() + ", " + m.getMvGenre() + ", " + m.getMvLocation());
		}
		
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
	//------------------------------------------------------------------------------------------------
	/**
	 * @author 창훈
	 * @since jdk 17
	 * @param movieList : 전체 영화 리스트
	 * @return MovieDTO : 예매하려고 선택한 영화
	 * <p>전체 영화 리스트를 출력하고, 유저에게 원하는 영화를 입력받아 리스트 중 영화제목이 일치하는 데이터를 반환하는 메서드</p>
	 */
	public MovieDTO movieSelectByStream(List<MovieDTO> movieList)
	{
		if(movieList.isEmpty()) {
			System.out.println("예매 가능한 영화가 없습니다");
		}
		
		// 영화 리스트 출력
		movieList.stream().forEach(System.out::println);
		
		System.out.println("예매할 영화를 선택해주세요 : ");
		String title = sc.nextLine();
		return movieList.stream().filter(x-> x.getMvTitle().equals(title)).findFirst().orElse(null);
	}


	public String selectDate(String str) { // 상영하고 싶은 날짜 선택

		
		System.out.print(str);
		 
		return sc.nextLine();
	}
	

	public void myReservation(List<JoinDTO> list) { // 2-2. 내 예매 내역 확인하기
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		
		System.out.println("내 예매 내역 조회"); 
		for (JoinDTO m : list) {
			System.out.println(m.getRevNum() + ", " + m.getMvTitle() + " , " + m.getRevShowDate() + ", " + m.getRevRegDate() + " , " + m.getMvLocation());
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	/**
	 * @author 창훈
	 * @since jdk 17
	 * @param list : 조회한 예매내역 리스트
	 * <p>전체 예매내역을 출력하는 메소드</p>
	 */
	public void myReservationByStream(List<JoinDTO> list) { 
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		
		System.out.println("내 예매 내역 조회"); 
		list.stream().forEach(System.out::println);
	}
	//------------------------------------------------------------------------------------------------------------------------
	public int editReservation() { // 2-3. 내 예매 수정하기
		
		System.out.println("내 예매 수정하기");
		System.out.println("수정할 예매 번호를 선택해주세요 ! ");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
		
	}

	public int cancelReservation(List<JoinDTO> list) { // 2-4. 예매 취소하기
		
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}
		
	
		System.out.println("예매 취소하기");
		
		System.out.println("취소하고 싶은 예매번호를 입력해주세요");
		int select = sc.nextInt();
		sc.nextLine();
		
		for(JoinDTO m : list) {
			if(m.getRevNum() == select)
			{
				return m.getRevNum();
			}
		}
		return 0;
		
	}
//	-----------------------------------------------------
	/**
	 * @author 창훈
	 * @since jdk 17
	 * @param list : 전체 예약 리스트
	 * @return int : 예약 취소할 예약번호
	 * <p>전체 예약리스트 출력후 유저에게 예약번호를 선택받아 해당 번호를 반환하는 메소드입니다.
	 * 잘못된 번호를 입력 시, 반환되지 않도록 처리했습니다.</p>
	 */
public int cancelReservationByStream(List<JoinDTO> list) {
		
		if(list.isEmpty()) {
			System.out.println("예매 내역이 없습니다!");
		}

		System.out.println("취소하고 싶은 예매번호를 입력해주세요");
		int select = sc.nextInt();
		sc.nextLine();
		
		JoinDTO selectedDTO = list.stream().filter(x->x.getRevNum() == select).findFirst().orElse(null); 
		return selectedDTO == null ? 0 : selectedDTO.getRevNum();
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
	
	// 메시지 출력 메소드
	public void msg(String msg) {
		System.out.println(msg);
	}

}
