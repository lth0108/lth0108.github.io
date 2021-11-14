package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import member.model.dto.MemberDTO;

public class MemberDAO33 {
	
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
	public ArrayList<MemberDTO> getSelectAll() {
		getConn();
		ArrayList<MemberDTO> list = new ArrayList<>();
		
		try {
			String sql = "select * from member order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddr(rs.getString("addr"));
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
		public MemberDTO getSelectOne(int no) {
			getConn();
			MemberDTO dto = new MemberDTO();
			try {
				String sql = "select * from member where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setNo(rs.getInt("no"));
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setEmail(rs.getString("email"));
					dto.setAddr(rs.getString("addr"));
					dto.setRegi_date(rs.getDate("regi_date"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				getConnClose();
			}
			return dto;
		}
	
	
	//등록
	public int setInsert(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into member(no, id, password, name, phone, email, addr, regi_date) ";
			sql += " values(seq_member.nextval, ?, ?, ?, ?, ?, ?, sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getAddr());	
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	
	//수정
	public int setUpdate(MemberDTO dto) {
		getConn();
		int result = 0 ;
		 try {
			 String sql = "update member set name = ?, phone = ?, email = ?, addr = ? where no = ?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dto.getName());
			 pstmt.setString(2, dto.getPhone());
			 pstmt.setString(3, dto.getEmail());
			 pstmt.setString(4, dto.getAddr());
			 pstmt.setInt(5, dto.getNo());
			 
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
			String sql = "delete from member where no = ?";
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
	
	
	//로그인
	public MemberDTO getLogin(MemberDTO dto) {
		getConn();

		try {
			String sql = "select no, id, name from member where id = ? and password = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	
}
