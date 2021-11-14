package shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import product.model.dao.ProductDAO;
import product.model.dto.ProductDTO;
import shop.model.dao.ShopDAO;
import shop.model.dto.ShopDTO;

@WebServlet("/shop_servlet/*")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		System.out.println(url);
		
		MemberDAO dao_member = new MemberDAO();
		MemberDTO dto_member = new MemberDTO();
		ProductDAO dao_product = new ProductDAO();
		ProductDTO dto_product = new ProductDTO();
		ShopDAO dao_shop = new ShopDAO();
		ShopDTO dto_shop = new ShopDTO();
		
		ArrayList<ProductDTO> list_product = new ArrayList<>();
		ArrayList<ShopDTO> list_shop = new ArrayList<>();

//===================================================================		
		int cookNo = 0;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("cookNo") != null) {
			cookNo = (Integer) session.getAttribute("cookNo");
		}
		
		if(cookNo == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용하세요!');");
			out.println("location.href='" + path + "';");
			out.println("</script>");
			out.flush();
			out.close();
			return;
		}
//===================================================================
		
		if(url.contains("list.do") == true) {
			// 검색
			String search_option = request.getParameter("search_option");
			String search_data = request.getParameter("search_data");
			if (search_option == null || search_option.equals("") || search_data == null || search_data.equals("")) {
				search_option = "id";
				search_data = "%";
			}
			
			if (search_option.equals("id") && !search_data.trim().equals("")) {
				search_option = "id";
			} else if (search_option.equals("name") && !search_data.trim().equals("")) {
				search_option = "name";
			}

			list_product = dao_product.getSelectAll(search_option, search_data);

			request.setAttribute("list_product", list_product);
			
			String page = "/WEB-INF/shop/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("market.do")) {
			list_shop = dao_shop.getSelectAll(cookNo);
			
			int totalRecord = dao_shop.getTotalRecord();

			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("list_shop", list_shop);
			
			String page = "/WEB-INF/shop/market.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("marketChuga.do")) {
			String productNo_ = request.getParameter("productNo");
			int productNo = Integer.parseInt(productNo_);
			String amount_ = request.getParameter("amount");
			int amount = Integer.parseInt(amount_);
						
			dto_shop.setProductNo(productNo);
			dto_shop.setAmount(amount);
			dto_shop.setMemberNo(cookNo);
			
			int result = dao_shop.setInsert(dto_shop);

			if(result > 0) {
				response.sendRedirect(path + "/shop_servlet/list.do");
			} else {
				response.sendRedirect(path + "/shop_servlet/error.do?error_code=PC");
			}
		

		} else if(url.contains("view.do")) {
			String productNo_ = request.getParameter("no");
			int productNo = Integer.parseInt(productNo_);
			
			dto_product = dao_product.getSelectOne(productNo);
			request.setAttribute("dto_product", dto_product);
			
			String page = "/WEB-INF/shop/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("buyProc.do")) {
			String sum_ = request.getParameter("sum");
			int sum = 0;
			if(!(sum_ == null || sum_.trim().equals(""))) {
				sum = Integer.parseInt(sum_);
			}
			
			String no_[] = request.getParameterValues("chk");
			if(no_ != null) {
				int no[] = new int[no_.length];
				
				for(int i=0;i<no_.length;i++) {
					String[] no__ = no_[i].split("-");
					no[i] = Integer.parseInt(no__[1]);
					sum += no[i];
				}
			}
			
			
			dto_member = dao_member.getSelectOne(cookNo);
			
			sum += dto_member.getBuy();
			dto_member.setBuy(sum);
			
			if(sum >= 50000) {
				dto_member.setGrade("GOLD");
			} else if(sum >= 200000) {
				dto_member.setGrade("VIP");
			} else if(sum >= 500000) {
				dto_member.setGrade("VVIP");
			}
			
			int result = dao_member.setBuyUpdate(dto_member);

			if(result > 0) {
				response.sendRedirect(path + "/shop_servlet/list.do");
			} else {
				response.sendRedirect(path + "/shop_servlet/error.do?error_code=PC");
			}
			
			
			
		} else if(url.contains("sakje.do")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			int result = dao_shop.setDelete(no);
			 
			if(result > 0) {
				response.sendRedirect(path + "/shop_servlet/market.do"); 
			} else { 
				response.sendRedirect(path +"/shop_servlet/error.do?error_code=err03");
			}

		} else if(url.contains("cartClear.do")) {
//			장바구니 비우기(post / name배열)
			String no_[] = request.getParameterValues("chk");		
			int result = 0;
			
			if(no_ != null) {
				int no[] = new int[no_.length];

				for(int i=0;i<no_.length;i++) {
					String[] no__ = no_[i].split("-");
					no[i] = Integer.parseInt(no__[0]);
					result = dao_shop.setDelete(no[i]);
				}
			}

//			장바구니 비우기(post / i++)		
/*			String total_counter_ = request.getParameter("total_counter");
			int total_counter = Integer.parseInt(total_counter_);
			int result = 0;
			String str = "";
			
			for(int i=0; i<total_counter; i++) {
				String no_ = request.getParameter("chk_" + i);
				if(no_ != null) {
					str += "," + no_;
				}
			}
			str = str.substring(1);
			
			String[] arrayNo = str.split(",");
			for(int i=0;i<arrayNo.length;i++) {
				int no = Integer.parseInt(arrayNo[i]);
				
				result = dao_shop.setDelete(no);
			}
*/
		if(result > 0) {
			response.sendRedirect(path + "/shop_servlet/market.do");
		} else {
			response.sendRedirect(path +"/shop_servlet/error.do?error_code=err03");
		}
		
		} else if(url.contains("clearProc.do")) {
			String chk_no = request.getParameter("chk_no");
			String[] arrayNo = chk_no.split(",");

			for(int i=0;i<arrayNo.length;i++) {
				int no = Integer.parseInt(arrayNo[i]);
				dao_shop.setDelete(no);
			}
			
			response.sendRedirect(path + "/shop_servlet/market.do");
/*
			String chk_no = request.getParameter("chk_no");
			String[] array = chk_no.split(",");
	
			int result = dao_shop.setDeleteBatch(array);
		
			if(result > 0) {
				response.sendRedirect(path + "/shop_servlet/market.do");
			} else {
				response.sendRedirect(path +"/shop_servlet/error.do?error_code=err03");
			}
*/			
		} else if(url.contains("error.do")) {
		String error_code = request.getParameter("error_code");
		
		String memo = "";
		if(error_code.equals("err01")) {
			memo = "추가 처리중 오류가 발생했습니다!";
		} else if(error_code.equals("err02")) {
			memo = "수정 처리중 오류가 발생했습니다!";
		} else if(error_code.equals("err03")) {
			memo = "삭제 처리중 오류가 발생했습니다!";
		}
		
		request.setAttribute("memo", memo);
		request.setAttribute("error_code", error_code);
		
		String page = "/WEB-INF/shop/error.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
		}
		else {
			
		}
	}

}
