package com.koreait.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.koreait.dto.JoinDTO;
import com.koreait.dto.MemberDTO;
import com.koreait.dto.MovieDTO;

import com.koreait.dto.JoinDTO;
/**
 * @author Administrator
 * @since
 * <p> </p>
 * 
 */
public class MovieView {

	private Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * @return int : 사용자가 선택한 번호
	 * <p> 프로그램 시작 시 로그인 메뉴를 보여주기 위한 메소드 </p>
	 * 
	 */
	
	public int loginMenu() {
		try {
			System.out.println("안녕하세요!");
			System.out.println("1. 로그인하기");
			System.out.println("2. 회원가입하기");
			System.out.println("0. 종료");

			int choice = sc.nextInt();
			sc.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 종료합니다.");
			return 0;
		} catch (Exception e) {
			System.out.println("loginMenu() 알 수 없는 오류 발생");
			return 0;
		}
	}
	/**
	 * 
	 * @return String : 사용자가 입력한 아이디
	 * <p> 로그인하기 선택 시, 사용자에게 아이디를 입력받기 위한 메소드</p>
	 */
	public String inputId() {
		System.out.println("아이디를 입력해주세요! : ");
		String userId = sc.nextLine();
		
		return userId;
		
	}
	
	/**
	 * 
	 * @return String : 사용자가 입력한 비밀번호
	 * <p> 로그인하기 선택 시, 사용자에게 아이디를 입력 받은 이후, 비밀번호를 입력받기 위한 메소드</p>
	 */
	public String inputPw() {
		System.out.println("비밀번호를 입력해주세요 ! : ");
		String userPw = sc.nextLine();
		
		return userPw;
	}
	
	/**
	 * 
	 * @param m : 사용자의 정보가 담긴 MemberDTO 타입의 객체 m
	 * <p> 회원가입을 진행하기 위한 메소드, MemberDTO의 객체를 받고 현 메소드 내에서 setter를 통해 필드값을 저장한다 
	 * +) 전화번호도 문자열로 받는다 </p>
	 */
	
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
	
	/**
	 * 
	 * @return int : 사용자가 선택한 메뉴의 정수값
	 * <p> 사용자가 로그인 성공 시, 메인메뉴를 출력하기 위한 메소드, 선택값에 따라 세부 메뉴를 진행한다</p>
	 */
	public int mainMenu() {
		try {
			System.out.println("----메뉴선택----");
			System.out.println("1. 영화");
			System.out.println("2. 예매");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원탈퇴");
			System.out.println("0. 종료");
			System.out.print("선택 : ");
			int choice = sc.nextInt();
			sc.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return 0;
		} catch (Exception e) {
			System.out.println("mainMenu() 알 수 없는 오류 발생");
			return 0;
		}
	}

//	-----------------------------------------------
	
	/**
	 * 
	 * @return int : 사용자가 선택한 메뉴의 정수값
	 * <p> 사용자가 영화(1번) 메뉴를 선택했을 때의 영화 메뉴를 출력하기 위한 메소드 </p>
	 */
	public int movieMenu() { // 1. 영화 메뉴
		try {
			System.out.println("----영화 메뉴----");
			System.out.println("1. 전체 영화 보기");
			System.out.println("2. 영화 장르로 찾기");
			System.out.println("3. 영화 추가");
			System.out.println("4. 영화 삭제");
			System.out.println("0. 종료");
			System.out.println("선택 : ");

			int choice = sc.nextInt();
			sc.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return 0;
		} catch (Exception e) {
			System.out.println("movieMenu() 알 수 없는 오류 발생");
			return 0;
		}
	}

	/**
	 * 
	 * @param list : 현재 영화 관련 정보가 담긴 리스트를 매개변수로 받는다
	 * <p> 상영 가능한 영화 목록을 출력해주기 위한 메소드, 리스트 내의 원소가 있는지 검사하고 시작한다
	 * 		list 내에 담긴 객체를 반복문을 통해 하나씩 꺼내서 getter를 통해 정보를 출력해준다</p>
	 */
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
	/**
	 * 
	 * @return String : 사용자에게 입력받은 검색값(장르)
	 * <p> 사용자에게 찾고 싶은 영화의 장르를 입력받는 메소드 </p>
	 */
	public String findMovieWithGenre() { // 1-2. 영화 장르로 찾기
		
		System.out.println("영화 장르로 찾기");
		System.out.println("찾고 싶은 장르를 입력해주세요 : ");
		String t = sc.nextLine();
	
		return t;
	}

	/**
	 * 
	 * @return MovieDTO : 영화 정보가 담긴 객체
	 * <p> 영화 추가와 관련해서 필요한 출력들을 진행해주는 메소드, 
	 * 		사용자에게 제목, 장르, 가격, 상영관을 입력받고 setter로 필드를 저장한다
	 * 	</p> 
	 */
	public MovieDTO addMovie() { // 1-3. 영화 추가하기
		MovieDTO m = new MovieDTO();
		try {
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
		catch(InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return null;
		}
		catch(Exception e) {
			System.out.println("mainMenu() 알 수 없는 오류 발생");
			return null;
		}
	}
	
	/**
	 * 
	 * @return String : 사용자에게 입력받은 영화 제목
	 * <p> 사용자에게 삭제하고 싶은 영화 제목을 입력받는 메소드 </p>
	 */
	public String deleteMovie() { // 1-4. 영화 삭제하기
		System.out.println("영화 삭제하기");
		
		System.out.print("삭제할 영화의 제목을 입력해주세요 : ");
		String mvTitle = sc.nextLine();
		
		
		return mvTitle;
	}

//	-------------------------------------
	
	/**
	 * 
	 * @return int : 사용자가 선택한 메뉴 번호의 정수값
	 * <p> 사용자가 메인메뉴에서 예매를 선택했을 때의 출력을 진행하는 메소드 </p>
	 */
	public int showResvMenu() { // 2. 예매 메뉴
		try {
			System.out.println("----예매 메뉴----");
			System.out.println("1. 예매하기");
			System.out.println("2. 내 예매 내역");
			System.out.println("3. 예매 수정");
			System.out.println("4. 예매 취소");
			System.out.println("0. 종료");

			int choice = sc.nextInt();
			sc.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return 0;
		} catch (Exception e) {
			System.out.println("mainMenu() 알 수 없는 오류 발생");
			return 0;
		}
	}
	
	
	
	/**
	 * 
	 * @param list : 영화에 대한 정보가 담긴 리스트
	 * @return MovieDTO : 사용자가 입력한 영화 제목과 일치하는 영화 제목에 대한 객체
	 * <p> 사용자에게 예매하고자 하는 영화를 입력받기 위한 메소드,
	 * 		매개변수로 받은 list의 원소 존재 여부를 검사하고 시작한다.
	 * 		현재 상영 가능한 영화를 매개변수의 list를 반복문을 통해서 출력해주고,
	 * 		이 중에서 사용자가 예매하고 싶은 영화의 제목을 입력받는다.
	 * 		그리고 그 제목이 list내에 존재하는 영화의 제목과 일치하다면,
	 * 		새로운 객체에 담아서 반환한다. </p>
	 */
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

	
	/**
	 * 
	 * @param str : 사용자에게 날짜 선택을 받기 위해 출력해주는 문자열
	 * @return String : 사용자가 입력한 희망 상영 날짜
	 * <p> 사용자에게 상영하고 싶은 날짜를 입력받는 메소드</p>
	 */
	public String selectDate(String str) { // 상영하고 싶은 날짜 선택
		System.out.print(str);
		return sc.nextLine();
	}
	
	/**
	 * 
	 * @param list : 현재 내가 예매한 영화들의 객체가 담긴 라스트
	 * <p> 현재 로그인 된 사용자가 예매한 영화들의 정보를 보여주는 메소드, 
	 * 		반복문을 통해서 리스트의 정보를 전부 출력해준다</p>
	 */
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
	
	/**
	 * 
	 * @return int : 사용자가 입력한 예매 번호의 정수값
	 * <p> 사용자에게 수정하고자 하는 예매 번호를 입력받는 메소드 </p>
	 */
	public int editReservation() { // 2-3. 내 예매 수정하기
		
		System.out.println("내 예매 수정하기");
		System.out.println("수정할 예매 번호를 선택해주세요 ! ");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
		
	}
	
	/**
	 * 
	 * @param list : 현재 로그인 된 사용자가 예매한 영화의 리스트
	 * @return int : 사용자에게 입력받은 예매번호의 정수값
	 * <p> 사용자에게 취소하고자 하는 예매번호의 값을 입력받는 메소드, 
	 * 		예매 내역이 있는지 검사하고, 입력받은 예매번호가 현재 예매내역의 존재하는 예매번호인 경우에만 진행한다</p>
	 */
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
		try
		{
		System.out.println("취소하고 싶은 예매번호를 입력해주세요");
		int select = sc.nextInt();
		sc.nextLine();
		
		JoinDTO selectedDTO = list.stream().filter(x->x.getRevNum() == select).findFirst().orElse(null); 
		return selectedDTO == null ? 0 : selectedDTO.getRevNum();
		}
		catch(InputMismatchException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return 0;
		}
		catch(Exception e) {
			System.out.println("mainMenu() 알 수 없는 오류 발생");
			return 0;
		}
	}
	
	
//	-----------------------------------------------------
	
	/**
	 * 
	 * @return MemberDTO : 변경한 회원의 정보가 담긴 객체
	 * <p> 사용자에게 수정하고자 하는 정보를 입력받는 메소드, 이메일, 전화번호를 입력받는다</p>
	 */
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
	
	/**
	 * 
	 * @return int : 사용자가 회원 탈퇴여부에 대해서 입력한 정수값
	 * <p> 사용자에게 회원 탈퇴여부를 입력받고, 입력한 정수값을 반환해주는 메소드 </p>
	 */
	public int deleteAccount() { // 4. 회원 탈퇴하기

		try {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 네");
			System.out.println("2. 아니오");

			int choice = sc.nextInt();
			sc.nextLine();
			return choice;
		} catch (NumberFormatException e) {
			System.out.println("잘못된 숫자를 입력하셨습니다 이전 메뉴로 이동합니다.");
			return 0;
		} catch (Exception e) {
			System.out.println("deleteAccount() 알 수 없는 오류 발생");
			return 0;
		}
	}
	
	//	----------------------------------------------------
	
	/**
	 * 
	 * @param msg : 출력하기 위해 전달해주는 문자열
	 * <p> Controller 부분에서 메시지를 출력할 필요가 있을 때 사용하기 위해 만들어둔 메소드 </p>
	 */
	// 메시지 출력 메소드
	public void msg(String msg) {
		System.out.println(msg);
	}

}
