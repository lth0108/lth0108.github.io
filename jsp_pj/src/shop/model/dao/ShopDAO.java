package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DB;
import product.model.dto.ProductDTO;
import shop.model.dto.ShopDTO;

public class ShopDAO {
	
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
	
	
	//totalRecord
		public int getTotalRecord() {
			getConn();
			int result = 0;

			try {
				String sql = "select count(*) from cart ";
				pstmt = conn.prepareStatement(sql);
				
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
	
	
	//장바구니리스트
	public ArrayList<ShopDTO> getSelectAll(int cookNo) {
		getConn();
		ArrayList<ShopDTO> list_shop = new ArrayList<>();


		try {
			String sql = " select c.no cartNo, c.amount cartAmount, c.regi_date cartRegi_date, m.no memberNo, ";
			sql += "m.name memberName,  p.no productNo, p.name productName, p.price productPrice, p.PRODUCTINFO PRODUCTINFO ";
			sql += " from cart c, product p, member m where c.productNo = p.no and c.memberNo = m.no and m.no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cookNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopDTO dto = new ShopDTO();
				dto.setNo(rs.getInt("cartNo"));
				dto.setAmount(rs.getInt("cartAmount"));
				dto.setRegi_date(rs.getDate("cartRegi_date"));
				dto.setMemberNo(rs.getInt("memberNo"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setProductNo(rs.getInt("productNo"));
				dto.setProductName(rs.getString("productName"));
				dto.setPrice(rs.getInt("productPrice"));
				dto.setProductImgInfo(rs.getString("PRODUCTINFO"));
				dto.setTotPrice(dto.tot());
				
				list_shop.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list_shop;
	}


	public ArrayList<ShopDTO> getSelectShopProductGroup() {
		getConn();
		ArrayList<ShopDTO> list_shop = new ArrayList<>();


		try {
			String sql = "select p.name productName, sum(c.amount * p.price) totPrice from cart c ";
			sql += " inner join product p on c.productNo = p.no group by p.name";
			sql += " order by productName asc ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ShopDTO dto = new ShopDTO();
				dto.setProductName(rs.getString("productName"));
				dto.setTotPrice(rs.getInt("totPrice"));
				list_shop.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return list_shop;
	}
	
	
	//장바구니등록
	public int setInsert(ShopDTO dto_shop) {
		getConn();
		int result = 0;
		try {
			String sql = " insert into cart(no, memberNo, productNo, amount, regi_date) ";
			sql += " values(seq_cart.nextval, ?, ?, ?, sysdate) "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto_shop.getMemberNo());
			pstmt.setInt(2, dto_shop.getProductNo());
			pstmt.setInt(3, dto_shop.getAmount());
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
			 String sql = "update product set name = ?, price = ?, description = ? ";
			 if(dto.getProduct_img().equals("--") || dto.getProduct_img_original().equals("--")) {
				 sql += " where no = ?";
			 } else {
				 sql += " , PRODUCT_IMG = ?, PRODUCT_IMG_ORIGINAL = ? where no = ? ";
			 }
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dto.getName());
			 pstmt.setInt(2, dto.getPrice());
			 pstmt.setString(3, dto.getDescription());
			 if(dto.getProduct_img().equals("--") || dto.getProduct_img_original().equals("--")) {
				 pstmt.setInt(4, dto.getNo());
			 } else {
				 pstmt.setString(4, dto.getProduct_img());
				 pstmt.setString(5, dto.getProduct_img_original());
				 pstmt.setInt(6, dto.getNo());
			 }
			 
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
			String sql = "delete from cart where no = ?";
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
	
	
	//삭제 배치 처리
	public int setDeleteBatch(String[] array) {
		int[] count = new int[array.length];
		getConn();
		
		try {
			conn.setAutoCommit(false);
			String sql = "delete from cart where no = ?";
			pstmt = conn.prepareStatement(sql);

			for(int i=0;i<array.length;i++) {
				if(array[i].equals("no")) {
					continue;
				}
				pstmt.setInt(1, Integer.parseInt(array[i]));
				pstmt.addBatch();
			}
			count = pstmt.executeBatch();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			getConnClose();
		}
		int result = count.length;
		return result;
	}
}
