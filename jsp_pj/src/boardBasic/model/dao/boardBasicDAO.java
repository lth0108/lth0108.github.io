package boardBasic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import boardBasic.model.dto.boardBasicDTO;

import config.DB;

public class boardBasicDAO {
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
	public ArrayList<boardBasicDTO> getSelectAll() {
		getConn();
		ArrayList<boardBasicDTO> list = new ArrayList<>();
		
		try {
			String sql = "select * from boardBasic where re_step = 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardBasicDTO dto = new boardBasicDTO();
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setHit(rs.getInt("hit"));
				dto.setRegi_date(rs.getDate("regi_date"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list;
	}
	
	
	//댓글목록
	public ArrayList<boardBasicDTO> getSelectComment(int no) {
		getConn();
		ArrayList<boardBasicDTO> list = new ArrayList<>();
		
		try {
			String sql = "select * from boardBasic where ref = (select ref from boardBasic where no = ?) ";
			sql += " and re_step != 1 order by re_level asc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				boardBasicDTO dto = new boardBasicDTO();
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setHit(rs.getInt("hit"));
				dto.setRegi_date(rs.getDate("regi_date"));
				
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
	public boardBasicDTO getSelectOne(int no) {
		getConn();
		boardBasicDTO dto = new boardBasicDTO();
		try {
			String sql = "select * from boardBasic where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getString("password"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				dto.setHit(rs.getInt("hit"));
				dto.setRegi_date(rs.getDate("regi_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	
	//조회수
	public void setUpdateHit(int no) {
		getConn();
		try {
			String sql = "update boardBasic set hit = hit+1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
//			result = pstmt.executeUpdate();
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}

	
	//등록
	public int setInsert(boardBasicDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into boardBasic values(seq_boardBasic.nextval, (select nvl(max(no),0)+1 no from boardBasic), ";
			sql += " ?, ?, ?, ?, ?, (select nvl(max(ref),0)+1 ref from boardBasic), 1, 1, 0, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPassword());
	
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	
	//댓글 등록
	public int setInsertComment(boardBasicDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into boardBasic values(seq_boardBasic.nextval, (select nvl(max(no),0)+1 no from boardBasic), ";
			sql += " ?, ?, ?, ?, ?, (select ref from boardBasic where no = ?), ";
			sql += " (select re_step + 1 rs_step from boardBasic where no = ?), ";
			sql += " (select re_level + 1 rs_level from boardBasic where no = ?), 0, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPassword());
			pstmt.setInt(6, dto.getNo());
			pstmt.setInt(7, dto.getNo());
			pstmt.setInt(8, dto.getNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		} return result;
	}
	
	
	//댓글 등록re_level
	public void setUpdateRe_level(int no) {
		getConn();
		try {
			String sql = "update boardBasic set re_level = re_level+1 ";
			sql += " where ref = (select ref from boardBasic where no = ?) and ";
			sql += " re_level > (select re_level from boardBasic where no = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}


	//수정
	public int setUpdate(boardBasicDTO dto) {
		getConn();
		int result = 0 ;
		
		 try {
			 String sql = "update boardBasic set subject = ?, content = ?, email = ?  where no = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dto.getSubject());
			 pstmt.setString(2, dto.getContent());
			 pstmt.setString(3, dto.getEmail());
			 pstmt.setInt(4, dto.getNo());
			 
			 result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}


	//삭제
	public int setDelete(int ref) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from boardBasic where ref = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
}
