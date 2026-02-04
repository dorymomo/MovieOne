package com.koreait.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.dto.ReservationDTO;
import com.koreait.dto.joinDTO;

public class ReservationDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 예매 조회
	public List<joinDTO> reservationInfo(int memNum) {

		String query = "SELECT tmv.MV_TITLE, tm.MEM_NAME, tmv.MV_LOCATION, " + "tr.REV_REG_DATE, tr.REV_SHOW_DATE "
				+ "tr.REV_NUM  FROM TBL_RESERVATION tr " + "JOIN tbl_member tm ON tr.MEM_NUM = tm.MEM_NUM "
				+ "JOIN tbl_movie tmv ON tr.MV_NUM = tmv.MV_NUM " + "WHERE tr.MEM_NUM = ?";

		List<joinDTO> list = new ArrayList<>();

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, memNum);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				joinDTO dto = new joinDTO();

				dto.setMvTitle(resultSet.getString(1));
				dto.setMemNum(resultSet.getInt(2));
				dto.setMvLocation(resultSet.getString(3));
				dto.setRevRegDate(resultSet.getString(4));
				dto.setRevShowDate(resultSet.getString(5));
				dto.setRevNum(resultSet.getInt(6));

				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("reservationInfo 오류");
			e.printStackTrace();
		} finally {
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

		return list;
	}

	// 예매 하는 메소드
	// 매개변수는 영화 번호, 회원 번호, 상영날짜
	// 반환 타입은 boolean 영화 예매 성공 여부
	public boolean reservation(ReservationDTO dto) {

		String query = "INSERT INTO tbl_reservation " + "(rev_num, mv_num, mem_num, rev_reg_date, rev_show_date) "
				+ "VALUES (SEQ_RESERVATION.NEXTVAL, ?, ?, SYSDATE, ?)";

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, dto.getRevNum());
			preparedStatement.setInt(2, dto.getMvNum());
			preparedStatement.setDate(3, java.sql.Date.valueOf(dto.getRevShowDate()));

			int result = preparedStatement.executeUpdate();

			return result > 0; // 성공여부 반환

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	// 수정
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

	// 취소
	public boolean cancel(ReservationDTO dto) {
		String query = "DELETE FROM TBL_RESERVATION WHERE REV_NUM = ? AND MEM_NUM = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, dto.getRevNum());
			preparedStatement.setInt(2, dto.getMemNum());
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
