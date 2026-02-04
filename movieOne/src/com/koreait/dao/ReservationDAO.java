package com.koreait.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.dto.MovieDTO;
import com.koreait.dto.ReservationDTO;



public class ReservationDAO {
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	
	//예매 조회
	public List <ReservationDTO> reservationInfo() {
		
		String query ="SELECT REV_NUM, MV_NUM, MEM_NUM , REV_REG_DATE,REV_SHOW_DATE "  
			+ " FROM TBL_RESERVATION";
		
		List<ReservationDTO> list = new ArrayList<>();
		
		 try {
			connection = DBConnector.getConnection();
			 preparedStatement = connection.prepareStatement(query);
			 resultSet = preparedStatement.executeQuery();
			 
			 while(resultSet.next()) {
				 ReservationDTO rsv = new ReservationDTO();
				 rsv.setRevNum(resultSet.getInt(1));
				 rsv.setMvNum(resultSet.getInt(2));
				 rsv.setMemNum(resultSet.getInt(3));
				 rsv.setRevRegDate(resultSet.getString(4));
				 rsv.setRevShowDate(resultSet.getString(5));
				 
				 
				 list.add(rsv);
			 }
			 	
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("reservationInfo 오류 ");
			e.printStackTrace();
		}finally {
			  try {
				if(resultSet != null) {
				       resultSet.close();
				    }
				    if(preparedStatement != null) {
				       preparedStatement.close();
				    }
				    if(connection != null) {
				       connection.close();
				    }
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("reservationInfo 연결 종료 오류");
				e.printStackTrace();
			  }
			
		}
		
		 return list;
         
	}
	
	//예메하기 위한 영화의 정보를 보여주는 메소드 
	/**
	 * @public 
	 * @return
	 * 
	 */
	public List<MovieDTO> reservationMovieInfo(){
		String query = "SELECT mv_num,mv_title,mv_genre, mv_price,mv_location "
				+ "FROM tbl_movie";
		
		List<MovieDTO> list = new ArrayList<>();
		
		
		try {
			connection = DBConnector.getConnection();
			 preparedStatement = connection.prepareStatement(query);
			 resultSet = preparedStatement.executeQuery();
			 
			 while(resultSet.next()) {
				 MovieDTO md = new MovieDTO();
				 md.setMvNum(resultSet.getInt(1));        // mv_num
				 md.setMvTitle(resultSet.getString(2));   // mv_title
				 md.setMvGenre(resultSet.getString(3));   // mv_genre
				 md.setMvPrice(resultSet.getInt(4));      // mv_price
				 md.setMvLocation(resultSet.getString(5));// mv_location
				 
				 list.add(md);
			 }
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("reservation 오류 ");
			e.printStackTrace();
		}finally {
			  try {
				if(resultSet != null) {
				       resultSet.close();
				    }
				    if(preparedStatement != null) {
				       preparedStatement.close();
				    }
				    if(connection != null) {
				       connection.close();
				    }
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("reservation 연결 종료 오류");
				e.printStackTrace();
			  }
			
		}
		
		 return list;
		
	}
	
	//예매 하는 메소드 
	//매개변수는 영화 번호, 회원 번호, 상영날짜 
	//반환 타입은 boolean 영화 예매 성공 여부 
//	public boolean reservation(int mvNum, int memNum, String showDate) {
//		+
//	
//	}
	
	// 수정
	   public boolean updateShowDate(ReservationDTO dto) {
	      String query = "UPDATE TBL_RESERVATION SET REV_SHOW_DATE = ? WHERE REV_NUM = ? AND MEM_NUM = ?";
	      int result = 0;

	      try {
	         connection = DBConnector.getConnection();
	         preparedStatement = connection.prepareStatement(query);

	         preparedStatement.setDate(1,Date.valueOf(dto.getRevShowDate()));
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
