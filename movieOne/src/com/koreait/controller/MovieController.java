package com.koreait.controller;

import java.time.LocalDate;
import java.util.List;

import com.koreait.dao.MemberDAO;
import com.koreait.dao.MovieDAO;
import com.koreait.dao.ReservationDAO;
import com.koreait.dto.MemberDTO;
import com.koreait.dto.MovieDTO;
import com.koreait.dto.ReservationDTO;
import com.koreait.dto.JoinDTO;
import com.koreait.view.MovieView;

/**
 * @author 이창표
 * @since jdk 17
 *<p> 프로그램의 메인 컨트롤러이며 View와 DAO 사이를 연결한다 </p>
 *<p> View를 통한 사용자 입력에 값이 받고 DAO 호출해 결과를 다시 View로 전달해 출력한다.</p>
 * 전체적인 흐름도 
 * 사용자	1 로그인 	->	1 영화		영화 선택시 ->	1 영화 조회	예매 선택시 ->	1 영화 예매
 * 		2 회원가입 	2 예매					2 장르 조회				2 예매 조회
 * 					3 회원 정보 수정			3 영화 추가				3 예매 수정
 * 					4 회원 탈퇴				4 영화 삭제				4 예매 삭제
 */

public class MovieController {

	private MovieView view = new MovieView();
	private MemberDAO memDAO = new MemberDAO();
	private MovieDAO mvDAO = new MovieDAO();
	private ReservationDAO resDAO = new ReservationDAO();

	// 초기 유저 정보를 빈값으로 설정 -> 로그인시 사용자의 정보를 가짐
	private MemberDTO loginUser = null;

	// 메인 시작
	public void run() {
		while (true) {
			int menu = view.loginMenu();

			switch (menu) {
			case 1:
				//로그인
				login();
				break;
			case 2:
				//회원가입
				signUp();
				break;
			case 0:
				return;
			default:
				view.msg("숫자를 입력해주세요");
			}
		}
	}

	// 선택 메뉴
	private void checkMenu() {

		while (true) {
			int menu = view.mainMenu();

			switch (menu) {
			case 1:
				// 영화
				movieckMenu();
				break;
			case 2:
				// 예매
				resMenu();
				break;
			case 3:
				// 회원 정보수정()
				updateMember();
				break;
			case 4:
				// 회원탈퇴
				deleteMember();
				break;
			case 0:
				//로그아웃
				logout();
				return;
			default:
				view.msg("잘못된 입력입니다.");
			}
		}
	}

	// 영화 선택메뉴
	private void movieckMenu() {

		while (true) {
			int menu = view.movieMenu();

			switch (menu) {
			case 1:
				// 영화조회
				selectMoive();
				break;
			case 2:
				// 장르조회
				selectGenre();
				break;
			case 3:
				// 영화추가
				addMovie();
				break;
			case 4:
				// 영화삭제
				deleteMovie();
				break;
			case 0:
				return;
			default:
				view.msg("잘못된 입력입니다.");
			}
		}
	}
	
	// 예매 선택메뉴
	private void resMenu() {
		if (!loginCheck())
			return;

		while (true) {
			int menu = view.showResvMenu();

			switch (menu) {
			case 1:
				// 영화예매
				movieRes();
				break;
			case 2:
				// 예매조회
				selectRes();
				break;
			case 3:
				// 예매수정
				updateRes();
				break;
			case 4:
				// 예매취소
				cancelRes();
				break;
			case 0:
				return;
			default:
				view.msg("잘못된 입력입니다.");
			}
		}
	}

	/**
	 * 로그인 수행 
	 * view에서 id,pw 입력받고 DAO에서 로그인 시도
	 */
	private void login() {
		String id = view.inputId();
		String pw = view.inputPw();
		MemberDTO user = memDAO.login(id, pw);
		if (user != null) {
			loginUser = user;
			view.msg("로그인 성공!! " + user.getMemName() + "님 환영합니다.");
			checkMenu();
		} else {
			view.msg("로그인 실패");
		}
	}

	/**
	 * 회원가입 및 ID 중복체크 실행 
	 * view에서 받은 회원 정보 받고 ckID 통해 아이디 중복체크 진행 중복이 아니라면 회원가입을 진행
	 */
	private void signUp() {
		MemberDTO dto = new MemberDTO();
		view.inputjoin(dto);

		boolean ckId = memDAO.checkID(dto.getMemId());
		if (!ckId) {
			view.msg("이미 사용 중인 아이디입니다.");
			return;
		}

		boolean result = memDAO.join(dto);
		if (result) {
			view.msg("회원가입 성공! 로그인 다시 해주세요.");
		} else {
			view.msg("회원가입 실패");
		}

	}

	/**
	 * 로그인 체크 진행
	 * 현재 상태가 로그인한 상태인지 체크 
	 */
	private boolean loginCheck() {
		if (loginUser == null) {
			view.msg("로그인이 필요합니다.");
			return false;
		}
		return true;
	}

	/**
	 * 로그아웃 
	 * 사용자가 로그아웃시 현재의 로그인정보를 빈값으로 변경 
	 */
	private void logout() {
		loginUser = null;
		view.msg("로그아웃 되었습니다.");
	}

	/**
	 * 회원정보 수정 
	 * 회원의 전화번호와 이메일 수정 가능
	 */
	private void updateMember() {
		MemberDTO dto = view.userChange();
		dto.setMemNum(loginUser.getMemNum());
		boolean result = memDAO.changeEmailAndPhoneNo(dto);

		view.msg(result ? "회원수정 성공" : "회원수정 실패");
	}

	/**
	 * 회원 탈퇴
	 * 회원 탈퇴를 진행
	 */
	private void deleteMember() {
		view.deleteAccount();
		boolean result = memDAO.signOutMember(loginUser.getMemNum());
		view.msg(result ? "회원탈퇴 성공" : "회원탈퇴 실패");
	}
	/**
	 * 영화 조회
	 * 현재 상영하는 영화 목록을 출력
	 */
	private void selectMoive() {
		List<MovieDTO> list = mvDAO.movieList();
		view.showMovieList(list);
	}
	/**
	 * 영화의 장르별로 조회
	 * 장르별로 따로 영화 목록 출력
	 */
	private void selectGenre() {
		List<MovieDTO> list = mvDAO.mvGenreList(view.findMovieWithGenre());
		view.showMovieList(list); 
	}
	/**
	 * 영화 목록에서 영화를 추가
	 */
	private void addMovie() {
		MovieDTO dto = view.addMovie();
		boolean result = mvDAO.mvInsert(dto);
		view.msg(result ? "영화추가 성공" : "영화추가 실패");
	}
	/**
	 * 영화 목록에서 영화 삭제 
	 */
	private void deleteMovie() {
		boolean result = mvDAO.movieDelete(view.deleteMovie());
		view.msg(result ? "영화삭제 성공" : "영화삭제 실패");
	}
	/**
	 * 예매 상태를 조회
	 * 사용자의 예매목록 출력
	 */
	private void selectRes() {
		List<JoinDTO> list = resDAO.reservationInfo(loginUser.getMemNum());
		view.myReservation(list);
	}

	/**
	 * @author 창훈
	 * @since jdk17
	 * <p>영화 예매에 대한 메서드입니다.</p>
	 */
	private void movieRes() {
		// 영화목록 호출
		List<MovieDTO> lstMovie = mvDAO.movieList();
		// 영화 선택
		MovieDTO movieDto = view.movieSelect(lstMovie);
		// 선택한 영화가 존재하는지 여부 체크
		if (movieDto == null) {
			view.msg("영화 선택이 잘못되었습니다.");
			return;
		}
		// 예매 DTO 생성
		ReservationDTO newReservation = new ReservationDTO();
		// 예매 DTO 값 대입
		newReservation.setMemNum(this.loginUser.getMemNum());
		newReservation.setMvNum(movieDto.getMvNum());
		newReservation.setRevRegDate(LocalDate.now().toString());
		newReservation.setRevShowDate(view.selectDate("상영하고 싶은 날짜를 선택해주세요 : "));
		// 예매 테이블에 insert
		boolean result = resDAO.reservation(newReservation);
		// 결과 출력
		view.msg(result ? "예매 성공" : "예매 실패");
	}

	/**
	 * 예메 취소 
	 * 사용자의 예매를 취소
	 */
	private void cancelRes() {
		int number = view.cancelReservation(resDAO.reservationInfo(this.loginUser.getMemNum()));
		boolean result = resDAO.cancel(number, loginUser.getMemNum());
		view.msg(result ? "예매취소 성공" : "예매취소 실패");
	}
	/**
	 * 예매 수정
	 * 사용자가 시청 날짜를 수정
	 */
	private void updateRes() {
		ReservationDTO dto = new ReservationDTO();
		dto.setRevNum(view.editReservation());
		dto.setMemNum(loginUser.getMemNum());
		dto.setRevShowDate(view.selectDate("변경하실 날짜를 입력해주세요. :"));
		boolean result = resDAO.updateShowDate(dto);
		view.msg(result ? "예매수정 성공" : "예매수정 실패");
	}

}
