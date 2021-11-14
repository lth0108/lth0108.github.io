package memo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memo.model.dao.MemoDAO;
import memo.model.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURL().toString();
		String path = request.getContextPath();
		
		MemoDTO dto = new MemoDTO();
		MemoDAO dao = new MemoDAO();
		List<MemoDTO> list = new ArrayList<>();
		
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
				search_option = "writer";
				search_data = "%";
			}
			
			if (search_option.equals("writer") && !search_data.trim().equals("")) {
				search_option = "writer";
			} else if (search_option.equals("content") && !search_data.trim().equals("")) {
				search_option = "content";
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
			
			int jj = totalRecord - (pageNumber - 1) * pageSize; 
			int startRecord = (pageNumber -1 ) * pageSize + 1; 
			int lastRecord = pageNumber * pageSize; 
			if(lastRecord > totalRecord) { lastRecord =	totalRecord; }
			  
			int totalPage = 0; 
			int startPage = 1; 
			int lastPage = 1; 
			if(totalRecord > 0) {
				totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
				startPage = (pageNumber - 1) / blockSize * blockSize + 1; lastPage = startPage + blockSize - 1;
				if(lastPage > totalPage) { lastPage = totalPage; }
			}
			
			request.setAttribute("totalRecord", totalRecord);
			list = dao.getSelectAll(search_option, search_data, startRecord, lastRecord);
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/memo/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do") == true) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			if(content == null || content.equals("")) {
				content = "내용없음";
			}
			
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = 0;
			result = dao.setInsert(dto);
			
			String msg = "";
			String move = "";
			
			if(result > 0) {
				msg = "등록되었습니다!";
				move = path + "/memo_servlet/list.do";
			} else {
				msg = "등록에 실패했습니다!";
				//move = path + "/memo_servlet/list.do";
				move = "javascript:history.back();";
			}
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("location.href='" + move + "';");
			out.println("</script>");
			out.flush();
			out.close();
			return;

		} else if(url.contains("sujungProc.do") == true) {
			int no = Integer.parseInt(no_);
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			if(content == null || content.equals("")) {
				content = "내용없음";
			}
			
			dto.setNo(no);
			dto.setWriter(writer);
			dto.setContent(content);
			
			int result = 0 ;
			result = dao.setUpdate(dto);
			
			if(result > 0) {
				String page = path + "/memo_servlet/list.do";
				response.sendRedirect(page);
			} else {
				String page = path + "/memo_servlet/list.do";
				response.sendRedirect(page);
			}
			
		} else if(url.contains("sakjeProc.do") == true) {
			int no = Integer.parseInt(no_);
			
			int result = dao.setDelete(no);
			
			if(result > 0) {
				String page = path + "/memo_servlet/list.do";
				response.sendRedirect(page);
			} else {
				String page = path + "/memo_servlet/list.do";
				response.sendRedirect(page);
			}
		}
		
		
		
	}

}
