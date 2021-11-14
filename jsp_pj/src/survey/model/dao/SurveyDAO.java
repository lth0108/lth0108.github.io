package survey.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import survey.model.dto.SurveyDTO;

public class SurveyDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConn() {
		try {
			conn = DB.dbConn();
			System.out.println("-- 오라클 접속 성공 --");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-- 오라클 접속 실패 --");
		}
	}
	
	public void getConnClose() {
		DB.dbConnClose(rs, pstmt, conn);
	}
	
	//목록
	public ArrayList<SurveyDTO> getSelectAll() {
		getConn();
		ArrayList<SurveyDTO> list = new ArrayList<>();
		int i = 0;
		
		try {
			String sql = "select * from survey order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setAns1(rs.getString("ans1"));
				dto.setAns2(rs.getString("ans2"));
				dto.setAns3(rs.getString("ans3"));
				dto.setAns4(rs.getString("ans4"));
				dto.setStatus(rs.getString("status"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list;
	}
	
	
	//뷰
		public SurveyDTO getSelectOne(int no) {
			getConn();
			SurveyDTO dto = new SurveyDTO();
			try {
				String sql = "select * from survey where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setNo(rs.getInt("no"));

					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				getConnClose();
			}
			return dto;
		}
	
		
	//등록
	public int setInsert(SurveyDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into survey values((select (max(no)+1) no from survey), ";
			sql += " ?, ?, ?, ?, ?, ?, ?, ?, sysdate))";
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dto.getName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	
	//수정
	public int setUpdate(SurveyDTO dto) {
		getConn();
		int result = 0 ;
		
		 try {			 
			 String sql = "update product set name = ?, price = ?, description = ?, product_img = ?, ";
			 sql += " product_img_original = ?, PRODUCTINFO = ? where no = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
	
			 
			 result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	

	//삭제
	public int setDelete(int no) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from product where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
}
