package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import product.model.dto.ProductDTO;

public class ProductDAO {
	
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
	public ArrayList<ProductDTO> getSelectAll(String search_option, String search_data) {
		getConn();
		ArrayList<ProductDTO> list = new ArrayList<>();
		int i = 0;
		
		try {
			String sql = "select * from product ";
			sql += " where 1 = 1 and name like ? ";
			sql += "order by no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + search_data + '%');
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_img_original(rs.getString("product_img_original"));
				dto.setProductImgInfo(rs.getString("PRODUCTINFO"));
				dto.setRegi_date(rs.getDate("regi_date"));
				dto.setRownum(i);
				i++;
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list;
	}
	
	
	//totalRecord
	public int getTotalRecord(String search_option, String search_data) {
		getConn();
		int result = 0;
		int k = 0;

		try {
			String sql = "select count(*) from product where 1 = 1";
			sql += " and name like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + search_data + '%');
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		} 
		return result;
	}
	
	
	//뷰
		public ProductDTO getSelectOne(int no) {
			getConn();
			ProductDTO dto = new ProductDTO();
			try {
				String sql = "select * from product where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setDescription(rs.getString("description"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setProduct_img_original(rs.getString("product_img_original"));
					dto.setProductImgInfo(rs.getString("productInfo"));
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
	public int setInsert(ProductDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into product(no, name, price, description, PRODUCT_IMG, PRODUCT_IMG_ORIGINAL, ";
			sql += " productInfo, regi_date)  values(seq_product.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			pstmt.setString(5, dto.getProduct_img_original());
			pstmt.setString(6, dto.getProductImgInfo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	
	//수정
	public int setUpdate(ProductDTO dto) {
		getConn();
		int result = 0 ;
		
		 try {			 
			 String sql = "update product set name = ?, price = ?, description = ?, product_img = ?, ";
			 sql += " product_img_original = ?, PRODUCTINFO = ? where no = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dto.getName());
			 pstmt.setInt(2, dto.getPrice());
			 pstmt.setString(3, dto.getDescription());
			 pstmt.setString(4, dto.getProduct_img());
			 pstmt.setString(5, dto.getProduct_img_original());
			 pstmt.setString(6, dto.getProductImgInfo());
			 pstmt.setInt(7, dto.getNo());
			 
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
