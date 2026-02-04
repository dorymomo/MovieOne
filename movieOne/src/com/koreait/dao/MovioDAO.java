package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.dto.MovieDTO;

//- 준승  영화조회, 영화삭제    
//view : 준승
public class MovioDAO {
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	
	
	//영화조회 메소드 > 영화의 전체 리스트를 보여주는 것으로 합의
	
	public List<MovieDTO> movieList(MovieDTO movieDTO) {
		
		List<MovieDTO> list = new ArrayList<>();
		
		String query = "SELECT mv_num, mv_title, mv_genre, mv_price, mv_location FROM tbl_movie";
		
		
		try {
			connection = DBConnector.getConnection();
			
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				MovieDTO mov = new MovieDTO();
				mov.setMvNum(resultSet.getInt(1));
				mov.setMvTitle(resultSet.getString(2));
				mov.setMvGenre((resultSet.getString(3)));
				mov.setMvPrice((resultSet.getInt(4)));
				mov.setMvLocation((resultSet.getString(5)));
				
				list.add(mov);
				
			}
		} catch (SQLException e) {
			System.out.println("movieList() sql 오류");
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
				System.out.println("movieList() 연결 해제 오류");
				e.printStackTrace();
			}
		}
		
		
		return list;
		
		
	}
	
	
	//영화삭제 메소드
	public boolean movieDelete(String mvTitle) {
		
		String query = "delete from tbl_movie where mv_title = ?";
		
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, mvTitle);
			
			int result = preparedStatement.executeUpdate();
			
			return result > 0;
		} catch (SQLException e) {
			System.out.println("movieDelete() sql 연결 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("movieDelete() 연결 해제 오류");
				e.printStackTrace();
			}
		}
		return false;
	}
}
