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
public class MovieDAO {
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	
	
	//영화조회 메소드 > 영화의 전체 리스트를 보여주는 것으로 합의
	
	public List<MovieDTO> movieList() {
		
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
	
	

	/** 
	 * @param movieDTO
	 * @author ktw
	 * @since jdk-17
	 * @return boolean
	 * 
	 * <p>영화 장르 조회(장르를 검색시에 해당 카테고리에 있는 영화 리스트 반환)
	 *  입력을 받는걸 만들고, 장르가 반환되게 해야함</p>
	 */

		
		public List<MovieDTO> mvGenreList(String genre) {

		    List<MovieDTO> list = new ArrayList<>();

		    String qeury =
		        "SELECT mv_title, mv_genre, mv_price, mv_location " +
		        "FROM tbl_movie " +
		        "WHERE mv_genre = ?";

		    try {
		        connection = DBConnector.getConnection();
		        preparedStatement = connection.prepareStatement(qeury);

		        // 장르 바인딩 (예: "애니메이션", "sf", "드라마")
		        preparedStatement.setString(1, genre);

		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            MovieDTO movie = new MovieDTO();
		            movie.setMvTitle(resultSet.getString("mv_title"));
		            movie.setMvGenre(resultSet.getString("mv_genre"));
		            movie.setMvPrice(resultSet.getInt("mv_price"));
		            movie.setMvLocation(resultSet.getString("mv_location"));
		            list.add(movie);
		        }

		    } catch (SQLException e) {
		        System.out.println("mvGenreList() SQL 오류");
		        e.printStackTrace();
		    } finally {
		        try {
		            if (resultSet != null) resultSet.close();
		            if (preparedStatement != null) preparedStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            System.out.println("mvGenreList() 자원 해제 오류");
		            e.printStackTrace();
		        }
		    }

		    return list;
		}
	
		
		/** 
		 * @param movieDTO
		 * @author ktw
		 * @since jdk-17
		 * @return boolean
		 * 
		 * <p>영화 추가(사용자 입장에서 볼 영화 추가하는 식의 메소드)
		 *  그냥 사용자가 전부 입력해서 추가하는 느낌 영화테이블에 추가되는거임 그렇게 진행하도록 함</p>
		 */

		   public boolean mvInsert(MovieDTO movieDTO) {
			      String query = "INSERT INTO tbl_movie " + "VALUES (seq_movie.NEXTVAL, ?, ?, ?, ?)";
			      int result = 0;

			      try {
			         connection = DBConnector.getConnection();
			         preparedStatement = connection.prepareStatement(query);
			         // ?바인딩
			         preparedStatement.setString(1, movieDTO.getMvTitle());
			         preparedStatement.setString(2, movieDTO.getMvGenre());
			         preparedStatement.setInt(3, movieDTO.getMvPrice());
			         preparedStatement.setString(4, movieDTO.getMvLocation());
			         // 쿼리 실행
			         result = preparedStatement.executeUpdate();
			      } catch (SQLException e) {
			         // TODO Auto-generated catch block
			         System.out.println("mvInsert() SQL 오류!!");
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
			            // TODO Auto-generated catch block
			            System.out.println("mvInsert() 연결 종료 오류!");
			            e.printStackTrace();
			         }

			      }
			      return result > 0;
			   }
	
	

}
