package com.koreait.controller;

import java.util.List;

import com.koreait.dao.MemberDAO;
import com.koreait.dao.MovioDAO;
import com.koreait.dao.ReservationDAO;
import com.koreait.dto.MemberDTO;
import com.koreait.dto.MovieDTO;
import com.koreait.dto.ReservationDTO;
import com.koreait.view.MovieView;

public class MovieController {

	private MovieView view = new MovieView();
	private MemberDAO memDAO = new MemberDAO();
	private MovioDAO mvDAO = new MovioDAO();
	private ReservationDAO resDAO = new ReservationDAO();

	private MemberDTO loginUser = null;

	// 메인 시작
	public void run() {
		while (true) {
			int menu = view.loginMenu(); // view 메뉴

			switch (menu) {
			case 1:
				login();
				break;
			case 2:
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
				// 예매조회
				selectRes();
				break;
			case 2:
				// 영화예매
				movieRes();
				break;
			case 3:
				// 예매취소
				cancelRes();
				break;
			case 4:
				// 예매수정
				updateRes();
				break;
			case 0:
				return;
			default:
				view.msg("잘못된 입력입니다.");
			}
		}
	}

	// 로그인
	private void login() {
		MemberDTO dto = view.inputlogin();
		MemberDTO user = memDAO.login(dto.getMemId(), dto.getMemPw());
		if (user != null) {
			loginUser = user;
			view.msg("로그인 성공!! " + dto.getMemName() + "님 환영합니다.");
			checkMenu();
		} else {
			view.msg("로그인 실패");
		}
	}

//   회원가입 
	private void signUp() {
		MemberDTO dto = view.inputjoin();
		boolean result = memDAO.join(dto);

		if (result) {
			view.msg("회원가입 성공! 로그인 다시 해주세요.");
		} else {
			view.msg("회원가입 실패");
		}

//      if (result) {
//           MemberDTO user = memDAO.login(dto.getMemId(), dto.getMemPw());
//
//           if (user != null) {
//               loginUser = user;            
//               view.msg("회원가입 성공! 자동 로그인 완료!");
//               checkMenu();                  
//           } else {
//               view.msg("자동로그인 실패! 다시 로그인 해주세요!");
//           }

	}

	// 로그인 체크 유무
	private boolean loginCheck() {
		if (loginUser == null) {
			view.msg("로그인이 필요합니다.");
			return false;
		}
		return true;
	}

	// 로그아웃
	private void logout() {
		loginUser = null;
		view.msg("로그아웃 되었습니다.");
	}

	// 회원수정
	private void updateMember() {
//      if (!loginCheck()) return;
//		MemberDTO dto = view.inputUpdatemember(loginUser);
		MemberDTO dto = view.userChange();
		dto.setMemNum(loginUser.getMemNum());
		boolean result = memDAO.update(dto);

		view.msg(result ? "회원수정 성공" : "회원수정 실패");
	}

	// 회원탈퇴
	private void deleteMember() {
		view.deleteAccount();
		boolean result = memDAO.delete(loginUser.getMemNum());
		view.msg(result ? "회원탈퇴 성공" : "회원탈퇴 실패");
	}

	// 영화 조회
	private void selectMoive() {
		List<MovieDTO> list = mvDAO.movieList();
		view.showMovieList(list);
	}

	// 장르 조회
	private void selectGenre() {
		List<MovieDTO> list = mvDAO.selectGenres();
		view.findMovieWithGenre(list);
	}

	// 영화 추가
	private void addMovie() {
//      MovieDTO dto = view.inputAddMovie();
		MovieDTO dto = view.addMovie();
		boolean result = mvDAO.insertMovie(dto);
		view.msg(result ? "영화추가 성공" : "영화추가 실패");
	}

	// 영화 삭제
	private void deleteMovie() {
//      MovieDTO dto = view.inputMovieId();
		MovieDTO dto = view.deleteMovie();
		boolean result = mvDAO.deleteMovie(dto);

		view.msg(result ? "영화삭제 성공" : "영화삭제 실패");
	}

	// 예매 조회
	private void selectRes() {
		List<ReservationDAO> list = resDAO.selectMember(loginUser.getMemNum());
//		view.printResList(list);
		view.myReservation(list);
	}

	// 영화 예매
	private void movieRes() {
//      ReservationDTO dto = view.inputReservation();
		ReservationDTO dto = view.doReservation();
		dto.setMemNum(loginUser.getMemNum());
//         영화 존재여부 체크
//         if (!mvDAO.existsMovie(dto.getMvNum())) {
//              view.msg("없는 영화입니다.");
//              return;
//          }
		boolean result = resDAO.insert(dto);
		view.msg(result ? "예매 성공" : "예매 실패");
	}

	// 예매 취소
	private void cancelRes() {
//		ReservationDTO dto = view.inputCancelRes();
		ReservationDTO dto = view.cancelReservation();
		dto.setMemNum(loginUser.getMemNum());
		boolean result = resDAO.cancel(dto);
		view.msg(result ? "예매취소 성공" : "예매취소 실패");
	}

	// 예매 수정
	private void updateRes() {
//		ReservationDTO dto = view.inputUpdateShowDate();
		ReservationDTO dto = view.editReservation();
		dto.setMemNum(loginUser.getMemNum());

		boolean result = resDAO.updateShowDate(dto);
		view.msg(result ? "예매수정 성공" : "예매수정 실패");
	}

}
