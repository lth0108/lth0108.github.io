package product.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tika.Tika;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.model.dao.ProductDAO;
import product.model.dto.ProductDTO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
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
		
		ProductDAO dao = new ProductDAO();
		ProductDTO dto = new ProductDTO();
		ArrayList<ProductDTO> list = new ArrayList<>();
		String no_ = request.getParameter("no");
		
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
				search_option = "name";
				search_data = "%";
			}
			
			if (search_option.equals("name") && !search_data.trim().equals("")) {
				search_option = "name";
			}
			
			// 페이지번호
			String pageNumber_ = request.getParameter("pageNumber");
			if (pageNumber_ == null || pageNumber_.equals("")) {
				pageNumber_ = "1";
			}
			int pageNumber = Integer.parseInt(pageNumber_);

			int blockSize = 10;
			int pageSize = 10;
			
			System.err.println("totalrecord : " + dao.getTotalRecord(search_option, search_data));
			int totalRecord = dao.getTotalRecord(search_option, search_data);
			
			list = dao.getSelectAll(search_option, search_data);
			
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/product/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if(url.contains("view.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/product/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if(url.contains("chuga.do") == true) {
			String page = "/WEB-INF/product/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do") == true) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			int max_upload = 10 * 1024 * 1024;//10M, 최대 업로드 용량을 10M로 제한
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					isDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			MultipartRequest multi = new MultipartRequest(
					request, 
					upload_path, 
					max_upload,
					"utf-8", 
					new DefaultFileRenamePolicy());
			
			String upload_counter_ = multi.getParameter("upload_counter");
			int upload_counter = Integer.parseInt(upload_counter_);
			String[] array = new String[upload_counter];
			String productImgInfo = ""; 
			String imsi = "";
			
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String formTagName = (String) files.nextElement();
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);
				
				long fileSize = 0;
				String mimeType = "";
				java.io.File file = multi.getFile(formTagName);
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mimeType = tika.detect(file);
				}
				
				if(fileSize > 0 && mimeType != null && fileType.equals(mimeType)) {
					imsi = fileOrgName + "?" + fileName + "?" + fileSize + "?" + mimeType;
				} else {
					imsi = "-";
				}
				
				int temp = Integer.parseInt(formTagName);
				array[temp] = imsi;
			}
			
			for(int i=0;i<array.length;i++) {
				productImgInfo += "|" + array[i];
			}
			productImgInfo = productImgInfo.substring(1);
			
			dto.setProductImgInfo(productImgInfo);
			
			String name = multi.getParameter("name");
			String price_ = multi.getParameter("price");
			String description = multi.getParameter("description");
			int price = Integer.parseInt(price_);
			
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			dto.setProduct_img("-");
			dto.setProduct_img_original("-");
			dto.setProductImgInfo(productImgInfo);
			
			int result = dao.setInsert(dto);

			if(result > 0) {
				response.sendRedirect(path + "/product_servlet/list.do");
			} else {
				response.sendRedirect(path + "/product_servlet/error.do?error_code=PC");
			}
		
		} else if(url.contains("sujung.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			String imginfo = dto.getProductImgInfo();
			String[] info = imginfo.split("\\|");
			String[] img = info[0].split("\\?");
			
			request.setAttribute("dto", dto);
			
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			
			java.io.File f1 = new java.io.File(upload_path);
			java.io.File[] array = f1.listFiles();
			
			for(int i=0;i<array.length;i++) {
				if(array[i].getPath().contains(img[1])) {
					request.setAttribute("filePath", array[i]);
				}
			}

			String page = "/WEB-INF/product/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do") == true) {	
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			int max_upload = 10 * 1024 * 1024;//10M, 최대 업로드 용량을 10M로 제한
			
			java.io.File isDir = new java.io.File(upload_path);
			if(!isDir.exists()) {
				try {
					isDir.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			MultipartRequest multi = new MultipartRequest(
					request, 
					upload_path, 
					max_upload,
					"utf-8", 
					new DefaultFileRenamePolicy());
			
			no_ = multi.getParameter("no");
			String name = multi.getParameter("name");
			String price_ = multi.getParameter("price");
			String description = multi.getParameter("description");
			
			int price = Integer.parseInt(price_);
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);
			
			String upload_counter_ = multi.getParameter("upload_counter");
			int upload_counter = Integer.parseInt(upload_counter_);
			String[] array = new String[upload_counter];
			String productImgInfo = "";
			String imsi = "";

			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String formTagName = (String) files.nextElement();
				String fileOrgName = multi.getOriginalFileName(formTagName);
				String fileName = multi.getFilesystemName(formTagName);
				String fileType = multi.getContentType(formTagName);
				
				long fileSize = 0;
				String mimeType = "";
				java.io.File file = multi.getFile(formTagName);
				if(file != null) {
					fileSize = file.length();
					
					Tika tika = new Tika();
					mimeType = tika.detect(file);
				}
				
				if(fileSize > 0 && mimeType != null && fileType.equals(mimeType)) {
					imsi = fileOrgName + "?" + fileName + "?" + fileSize + "?" + mimeType;
				} else {
					imsi = "-";
				}
				
				int temp = Integer.parseInt(formTagName);
				array[temp] = imsi;
			}
			
			for(int i=0;i<array.length;i++) {
				productImgInfo += "|" + array[i];
			}
			
			productImgInfo = productImgInfo.substring(1);
			

			String img_info_old = dto.getProductImgInfo();
			String[] img_old_array = img_info_old.split("\\|");
			String[] img_new_array = productImgInfo.split("\\|");
			String productImgInfo_new = "";
			
			for(int i=0;i<img_old_array.length;i++) {
				if(!img_new_array[i].equals("-")) {
					if(!img_old_array[i].equals("-")) {
						String[] imsiArray = img_old_array[i].split("\\?");
						java.io.File f1 = new java.io.File(upload_path + "/" + imsiArray[1]);
						f1.delete();
					}
					img_old_array[i] = img_new_array[i];
				}
				productImgInfo_new += "|" + img_old_array[i];
			}
			
			productImgInfo_new = productImgInfo_new.substring(1);
			
			dto.setProductImgInfo(productImgInfo_new);

			
			int result = dao.setUpdate(dto);
			
			if(result > 0) {
				String page = path + "/product_servlet/view.do?no=" + no + "&error_code=sSuccess";
				response.sendRedirect(page);
			} else {
				String page = path + "/product_servlet/sujung.do?no=" + dto.getNo();
				response.sendRedirect(page);
			}
			
		} else if(url.contains("sakje.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			String temp = dto.getProductImgInfo();
			String[] product_img_info = temp.split("\\|");
			String[] img_info = new String[product_img_info.length];
			
			for(int i=0;i<product_img_info.length;i++) {
				if(!product_img_info[i].equals("-")) {
					img_info = product_img_info[i].split("\\?");
				}
			}
			request.setAttribute("dto", dto);
			
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			
			java.io.File f1 = new java.io.File(upload_path);
			java.io.File[] array = f1.listFiles();
			
			for(int i=0;i<array.length;i++) {
				if(array[i].getPath().contains(img_info[1])) {
					request.setAttribute("filePath", img_info[1]);
				}
			}
			
			String page = "/WEB-INF/product/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sakjeProc.do") == true) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			
			int no = Integer.parseInt(no_);
			dto = dao.getSelectOne(no);
			
			String filePath = dto.getProductImgInfo();
			String[] file_array = filePath.split("\\|");
			

			
			int result = dao.setDelete(no);
		
			if(result > 0) {
				for(int i=0;i<file_array.length;i++) {
					if(!file_array[i].equals("-")) {
						String[] img_info = file_array[i].split("\\?");
						java.io.File file = new java.io.File(upload_path + "/" + img_info[1]);
						if(file.exists()) {
							file.delete();
						}
					}
				}
				
				String page = path + "/product_servlet/list.do";
				response.sendRedirect(page);
			} else {
				response.sendRedirect(path + "/product_servlet/error.do?error_code=err03");
			}
			
		} else if(url.contains("cartClear.do")) {
			String no_str[] = request.getParameterValues("chk");		
			int result = 0;
			
			if(no_str != null) {
				int no[] = new int[no_str.length];

				for(int i=0;i<no_str.length;i++) {
					String[] no__ = no_str[i].split("-");
					no[i] = Integer.parseInt(no__[0]);
					result = dao.setDelete(no[i]);
				}
			}
		if(result > 0) {
			response.sendRedirect(path + "/product_servlet/list.do");
		} else {
			response.sendRedirect(path +"/product_servlet/error.do?error_code=err03");
		}
		
		} 
		else if(url.contains("error.do")) {
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
			
			String page = "/WEB-INF/product/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			}
	}
}
