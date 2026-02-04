package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.dto.MemberDTO;

public class MemberDAO {
	// 연결 관련 필드
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	// 김성민 회원 가입, 로그인
	/**
	 * @author 성민
	 * @param member : 회원정보를 담고있는 DTO객체
	 * @return boolean : 회원가입 성공 여부를 TRUE / FALSE
	 *         <p>
	 *         회원가입 메서드입니다. 전달받은 회원 정보를 이용하여 회원 등록이 정상적으로 완료하면 TRUE를 반환합니다.
	 *         </p>
	 */

	// 회원 가입 기능을 담당하는 join메소드입니다.
	// 매개변수로 DTO객체를 받아 모든 정보를 전달 받습니다
	// 반환타입은 BOOLEAN으로 성공 실패로 설계했습니다
	public boolean join(MemberDTO member) {

		// 쿼리문 작성
		// 22
		String query = "INSERT INTO TBL_MEMBER " + "(MEM_NUM, MEM_ID, MEM_PW, MEM_NAME, MEM_PHONENO, MEM_EMAIL) "
				+ "VALUES (SEQ_MEM.NEXTVAL, ?, ?, ?, ?, ?)";

		// 실행 결과를 저장하기 위한 변수선언입니다.
		int result = 0;

		// DB와 연결을 하기위한 코드입니다.
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// (?) 위 쿼리문 물음표 개수만큼 실제 회원 데이터를 바인딩합니다.
			preparedStatement.setString(1, member.getMemId());
			preparedStatement.setString(2, member.getMemPw());
			preparedStatement.setString(3, member.getMemName());
			preparedStatement.setString(4, member.getMemPhoneNo());
			preparedStatement.setString(5, member.getMemEmail());
			// 성공하면 1 실패하면 1을 반환합니다
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("join() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("join() 연결 종료 오류");
				e.printStackTrace();
			}
		}
		// 실행 결과가 1이상이면 TRUE, 아니면 FALSE를 반환합니다.
		return result > 0;
	}

	// 로그인 메소드
	/**
	 * @author 성민
	 * @param memId 회원 아이디
	 * @param memPw 회원 비밀번호
	 * @return String 로그인 성공 시 회원 이름, 실패 시 null 반환
	 *         <p>
	 *         로그인 메서드입니다. 전달받은 아이디와 비밀번호가 저장된 회원 정보와 일치하는지 검사하며, 일치할 경우 해당 회원의 이름을
	 *         반환합니다. 로그인에 실패하면 null을 반환합니다.
	 */

	// 로그인 기능을 담당하는 메소드입니다.
	// 매개변수로 아이디와 비밀번호를 받아 로그인이 정상적으로 가능한지 확인합니다.
	public String login(String memId, String memPw) {

		// 아이디와 비밀번호가 맞는지 확인하는 쿼리
		String query = "SELECT MEM_NAME FROM TBL_MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";
		// 결과를 저장하는 변수를 선언합니다 기본값은 NULL값으로 했습니다.
		String name = null;
		// DB와 연결을 하기위한 코드입니다.
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 물음표에 사용자가 입력한 아이디와 비밀번호를 바인딩합니다.
			preparedStatement.setString(1, memId);
			preparedStatement.setString(2, memPw);
			// 결과를 ResultSet에 저장합니다.
			resultSet = preparedStatement.executeQuery();
			// 로그인이 성공적으로 확인되면 회원 이름을 가져와서 NAME변수에 저장합니다.
			if (resultSet.next()) {
				name = resultSet.getString(1);
			}

		} catch (SQLException e) {
			System.out.println("login() sql 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("login() 연결 종료 오류");
				e.printStackTrace();
			}
		}

		return name;
	}

	/**
	 * @author 창훈
	 * @param memNum : 회원번호
	 * @return boolean : 회원 탈퇴 결과
	 *         <p>
	 *         회원탈퇴에 관련된 메서드입니다. 회원번호를 입력받아 해당하는 회원을 table에서 삭제하며, 삭제 성공했을 경우 true
	 *         반환
	 *         </p>
	 */
	public boolean signOutMember(int memNum) {
		// 쿼리 작성
		String query = "delete from tbl_member where mem_num = ?";
		// 쿼리 결과 저장용 변수
		int queryResult = 0;

		try {
			// 연결
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 1번째 매개인자에 memNum값 대입
			preparedStatement.setInt(1, memNum);

			queryResult = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// 오류 발생시 메시지
			System.out.println("signOutMember() SQL Error");
			e.printStackTrace();
		} finally {
			// 사용한 connect들 close
			try {
				// preparedStatement 사용했으므로, null 체크 후 close
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				// connection null 체크 후 close
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// 오류 발생시 메시지
				System.out.println("signOutMember() Connection Close Error");
				e.printStackTrace();
			}
		}

		// 결과 반환(쿼리 적용 성공 시 영향받은 결과행 1이상이므로 값 체크)
		return queryResult > 0;
	}

	/**
	 * @author 창훈
	 * @param memNum     : 수정할 대상의 회원번호
	 * @param newEmail   : 변경할 이메일
	 * @param newPhoneNo : 변경할 핸드폰번호
	 * @return queryResult > 0 : query 수행 결과 영향받은 행이 1개 이상 존재하는지 체크
	 *         <p>
	 *         이메일 및 핸드폰 번호 수정에 대한 메서드
	 *         </p>
	 */
	public boolean changeEmailAndPhoneNo(MemberDTO memberDTO) {
		// update 쿼리문 설정
		String query = "Update tbl_member set mem_email = ?, mem_phoneno = ? where mem_num = ?";
		// 결과 저장을 위한 변수 선언
		int queryResult = 0;

		// 연결
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			// 미완성 쿼리의 파라미터 대입
			preparedStatement.setString(1, memberDTO.getMemEmail());
			preparedStatement.setString(2, memberDTO.getMemPhoneNo());
			preparedStatement.setInt(3, memberDTO.getMemNum());
			// 쿼리 실행 후 결과 저장
			queryResult = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// 오류 발생시 메시지
			System.out.println("signOutMember() SQL Error");
			e.printStackTrace();
		} finally {
			// 사용한 connect들 close
			try {
				// preparedStatement 사용했으므로, null 체크 후 close
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				// connection null 체크 후 close
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// 오류 발생시 메시지
				System.out.println("signOutMember() Connection Close Error");
				e.printStackTrace();
			}
		}

		return queryResult > 0;
	}

}
