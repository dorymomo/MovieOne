package com.koreait.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.koreait.dto.MemberDTO;

	public class MemberDAO {
		// 연결 관련 필드
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		
		
		//김성민 회원 가입, 로그인
		
		private List<MemberDTO> memberList = new ArrayList<>();
		
		//회원가입 메소드
		
		public List<MemberDTO> join(MemberDTO memberDTO){
			System.out.println("아이디 입력 : ");
			memberDTO.setId(sc.nextLine());
			for(MemberDTO m : memberList) {
				if(m.getId().equals(memberDTO.getId())) {
					return memberList;
				}
			}
			
		System.out.println("비밀번호 입력 : ");
		memberDTO.setMemPw(sc.nextLine());
		System.out.println("이름 입력 : ");
		memberDTO.setMemName(sc.nextLine());
		System.out.println("전화번호 입력 : ");
		memberDTO.setMemPhoneNo(sc.nextLine());
		System.out.println("이메일 입력 : ");
		memberDTO.setMemEmail(sc.nextLine());
		sc.nextLine();
	    memberList.add(memberDTO);
	    return memberList;
		}
		
		//로그인 메소드
		
		public boolean login(String id, String pw) {
			for(MemberDTO m : memberList) {
				if(m.getId().equals(id) && m.getPw().equals(pw)) {
					return true;
				}
			}
			return false;
		}
		
		public boolean loginStream(String id, String pw) {
		      return memberList.stream().anyMatch(u -> u.getId().equals(id) && u.getPw().equals(pw));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
		/**
		 * @author 창훈
		 * @param memNum : 회원번호
		 * @return boolean : 회원 탈퇴 결과
		 * <p> 회원탈퇴에 관련된 메서드입니다. 회원번호를 입력받아 해당하는 회원을 table에서 삭제하며, 삭제 성공했을 경우 true 반환
		 * </p>
		 */
		public boolean signOutMember(int memNum) {
			// 쿼리 작성
			String query = "delete from tbl_member where mem_num = ?";
			// 쿼리 결과 저장용 변수
			int queryResult = 0;
			
			try
			{
				// 연결
				connection = DBConnector.getConnection();
				preparedStatement = connection.prepareStatement(query);
				// 1번째 매개인자에 memNum값 대입
				preparedStatement.setInt(1, memNum);
				
				queryResult = preparedStatement.executeUpdate();
			}
			catch (SQLException e)
			{
				// 오류 발생시 메시지 
				// TODO view 작업 되면 view 기능으로 처리되도록 수정
				System.out.println("signOutMember() SQL Error");
			}
			finally {
				// 사용한 connect들 close
				try
				{
					// preparedStatement 사용했으므로, null 체크 후 close
					if(preparedStatement != null) {
						preparedStatement.close();
					}
					
					// connection null 체크 후 close
					if(connection != null) {
						connection.close();
					}
				}
				catch (SQLException e)
				{
					// 오류 발생시 메시지 
					// TODO view 작업 되면 view 기능으로 처리되도록 수정
					System.out.println("signOutMember() Connection Close Error");
				}
			}
			
			// 결과 반환(쿼리 적용 성공 시 영향받은 결과행 1이상이므로 값 체크)
			return queryResult > 0;
		}
		
		/**
		 * @author 창훈 
		 * @param memNum : 수정할 대상의 회원번호
		 * @param newEmail : 변경할 이메일 
		 * @param newPhoneNo : 변경할 핸드폰번호
		 * @return queryResult > 0 : query 수행 결과 영향받은 행이 1개 이상 존재하는지 체크 
		 * <p> 이메일 및 핸드폰 번호 수정에 대한 메서드
		 * </p>
		 */
		public boolean changeEmailAndPhoneNo(int memNum, String newEmail, String newPhoneNo){
			// update 쿼리문 설정
			String query = "Update tbl_member set mem_email = ?, mem_phoneno = ? where mem_num = ?";
			// 결과 저장을 위한 변수 선언
			int queryResult = 0;
			
			// 연결
			try
			{
				connection = DBConnector.getConnection();
				preparedStatement = connection.prepareStatement(query);
				// 미완성 쿼리의 파라미터 대입
				preparedStatement.setString(1, newEmail);
				preparedStatement.setString(2, newPhoneNo);
				preparedStatement.setInt(3, memNum);
				// 쿼리 실행 후 결과 저장
				queryResult = preparedStatement.executeUpdate();
			}
			catch (SQLException e)
			{
				// 오류 발생시 메시지 
				// TODO view 작업 되면 view 기능으로 처리되도록 수정
				System.out.println("signOutMember() SQL Error");
			}
			finally {
				// 사용한 connect들 close
				try
				{
					// preparedStatement 사용했으므로, null 체크 후 close
					if(preparedStatement != null) {
						preparedStatement.close();
					}
					
					// connection null 체크 후 close
					if(connection != null) {
						connection.close();
					}
				}
				catch (SQLException e)
				{
					// 오류 발생시 메시지 
					// TODO view 작업 되면 view 기능으로 처리되도록 수정
					System.out.println("signOutMember() Connection Close Error");
				}
			}
			
			return queryResult > 0;
		}
		
		
}
