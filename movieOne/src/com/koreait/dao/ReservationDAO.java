package com.koreait.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.koreait.dto.ReservationDTO;
import com.koreait.dto.JoinDTO;

/**
 * @author 이해준
 * @version jdk17 
 * 
 * 영화 예매,예매 조회, 수정, 삭제의 메소드를 가진 클래스이다 
 * 
 */

public class ReservationDAO {

	/**
	 * Connection connection : java와 DBMS의 연결을 위한 Connection 인터페이스와 
	 * 해당 타입의 변수이다.
	 * 
	 * PreparedStatement PreparedStatement : java에서 작성된 쿼리문을 DBMS에 전달하는 
	 * 인터페이스이다. 해당 인터페이스의 타입의 변수이다. 
	 * 
	 * ResultSet resultSet : 쿼리문의 결과를 보여주는 인터페이스이다. 
	 * 해당 타입의 변수이다 
	 */
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	/**
	 * 예약 조회를 하는 메소드
	 * @param memNum
	 * @return list
	 * 
	 * 회원 번호(memNum)에 해당하는 예매 내역을 조회한다.
	 * 예매/회원/영화 테이블을 JOIN하여 영화 제목, 상영관, 예매일/상영일, 예매번호를 함께 반환한다.
	 */
	public List<JoinDTO> reservationInfo(int memNum) {

	
//	 영화의 이름, 회원번호,상영관,예약일,상영일,예약번호를 반환하는 쿼리문을 작성후 변수에 저장한다.
		String query = "SELECT tmv.MV_TITLE, tm.MEM_NUM, tmv.MV_LOCATION, " + "tr.REV_REG_DATE, tr.REV_SHOW_DATE, "
				+ "tr.REV_NUM  FROM TBL_RESERVATION tr " + "JOIN tbl_member tm ON tr.MEM_NUM = tm.MEM_NUM "
				+ "JOIN tbl_movie tmv ON tr.MV_NUM = tmv.MV_NUM " + "WHERE tr.MEM_NUM = ?";

	
//	 	JoinDTO리스트의 객체를 생성한다 
		List<JoinDTO> list = new ArrayList<>();

	
//	  	JAVA와 DBMS연결과 정보전달 중 예외가 발생할 경우를 대비하여 try-catch를 사용한다 
	 
		try {

//	 		DBMS와 JAVA 의 연결을 한 후, 해당 쿼리문을 DBMS에 요청한다 
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);


//	  		쿼리문에 정보를 입력한다.
			preparedStatement.setInt(1, memNum);

	
//	 	해당 쿼리문은 SELECT임으로 해당 쿼리문의 결과만을 가져온다 
//	  	해당 쿼리문을 DBMS에 요청후 해당 결과를 보여준다 
	
			resultSet = preparedStatement.executeQuery();


//	 	 결과문의 행을 next로 이동하며 확인한다 
	 
			while (resultSet.next()) {


//	 	JoinDTO의 객체를 생성한다 

				JoinDTO dto = new JoinDTO();


//	  	JoinDTO의 객체를 이용하여 해당값들의 결과를 입력한다 

				dto.setMvTitle(resultSet.getString(1));
				dto.setMemNum(resultSet.getInt(2));
				dto.setMvLocation(resultSet.getString(3));
				dto.setRevRegDate(resultSet.getString(4));
				dto.setRevShowDate(resultSet.getString(5));
				dto.setRevNum(resultSet.getInt(6));

//	 	해당 값들을 리스트에 더한다 

				list.add(dto);
			}


//	 	 메소드 실행 중 발생하는 문제를 예외처리한다 

		} catch (SQLException e) {
			System.out.println("reservationInfo 오류");
			e.printStackTrace();
		} finally {

//	 	 JAVA	와 DBMS의 연결 후 종료하기위해 역순으로 닫아준다
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//	 	 list를 반환한다 

		return list;
	}

	
	/**
	 * 예매를 하는 메소드 
	 * 매개변수로 ReservationDTO의 정보를 사용한다
	 * @param dto
	 * @return boolean
	 */
	public boolean reservation(ReservationDTO dto) {

//		영화번호, 회원번호, 상영일을 사용해 예매 정보를 추가(INSERT)하는 쿼리문
		String query = "INSERT INTO tbl_reservation " + "(rev_num, mv_num, mem_num, rev_reg_date, rev_show_date) "
				+ "VALUES (SEQ_RESERVATION.NEXTVAL, ?, ?, SYSDATE, ?)";


		
// 		DBMS와 JAVA 의 연결을 한 후, 해당 쿼리문을 DBMS에 요청한다 
//		해당 쿼리문을 연결하여 DBMS에 요청한다
//		값들을 전달한다. 
		try {
			connection = DBConnector.getConnection();
//			System.out.println(dto);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, dto.getMvNum());
			preparedStatement.setInt(2, dto.getMemNum());
			preparedStatement.setDate(3, Date.valueOf(dto.getRevShowDate()));

//		정보의 삽입이기 때문에 executeUpdate를 사용한다. 
			int result = preparedStatement.executeUpdate();

//		입력시 추가된 행의 갯수를 반환한다 (성공여부 반환)
			return result > 0; 


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//		DBMS와 연결을 다시 닫는다 
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		해당 값이 오류가 발생하거나 입력되지 않았다면 false를 반환한다
		return false;

	}

	/**
	 * @author 이창표
	 * @param dto
	 * @return boolean
	 * <p>예매번호,회원번호를 받아 영화 보는날을 변경하는 메소드 </p>
	 */
	public boolean updateShowDate(ReservationDTO dto) {
		String query = "UPDATE TBL_RESERVATION SET REV_SHOW_DATE = ? WHERE REV_NUM = ? AND MEM_NUM = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setDate(1, Date.valueOf(dto.getRevShowDate()));
			preparedStatement.setInt(2, dto.getRevNum());
			preparedStatement.setInt(3, dto.getMemNum());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("query 오류");
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
				System.out.println("updateShowDate() 연결 해제 오류");
				e.printStackTrace();
			}

		}
		return result > 0;

	}
	/**
	 * @author 이창표
	 * @param revNum
	 * @param memNum
	 * @return boolean
	 * <p>예매번호,맴버번호를 받아서 예약을 취소하는 메소드</p>
	 */
	public boolean cancel(int revNum, int memNum) {
		String query = "DELETE FROM TBL_RESERVATION WHERE REV_NUM = ? AND MEM_NUM = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, revNum);
			preparedStatement.setInt(2, memNum);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("query 오류");
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
				System.out.println("cancel() 연결 해제 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

}
