package guestBook.controller;

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

import guestBook.model.dao.GuestBookDAO;
import guestBook.model.dto.GuestBookDTO;

@WebServlet("/guestBook_servlet/*")
public class GuestBookController extends HttpServlet {
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
		String url = request.getRequestURI().toString();
		
		GuestBookDTO dto = new GuestBookDTO();
		GuestBookDAO dao = new GuestBookDAO();
		ArrayList<GuestBookDTO> list = new ArrayList<>();
		
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
				
		if(url.contains("list.do")) {
			list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/guestBook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("view.do")) {
			int no = Integer.parseInt(no_);
			
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/guestBook/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chuga.do")) {
			String page = "/WEB-INF/guestBook/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			name = name.trim().replace(" ", "");
			email = email.trim().replace(" ", "");
			content = content.trim().replace(" ", "");
			
			if(password.contains(" ")) {
				String move = path + "/guestBook_servlet/chuga.do";

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 형식의 비밀번호입니다!');");
				out.println("location.href='" + move + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
			
			dto.setName(name);
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setContent(content);
			
			int result = 0;
			result = dao.setInsert(dto);
			
			if(result > 0) {
				response.sendRedirect(path + "/guestBook_servlet/list.do");
			} else {
				response.sendRedirect(path + "/guestBook_servlet/error.do?error_code=err01");
			}
			
		} else if(url.contains("sujung.do")) {
			int no = Integer.parseInt(no_);
			
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/guestBook/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do")) {
			int no = Integer.parseInt(no_);
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			int result = 0;
			
			dto.setNo(no);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setContent(content);
			
			result = dao.setUpdate(dto);
			
			if(result > 0) {
				String page = path + "/guestBook_servlet/view.do?no=" + dto.getNo();
				response.sendRedirect(page);
			} else {
				String page = path + "/guestBook_servlet/error.do?error_code=err02";
				response.sendRedirect(page);
			}
			
		} else if(url.contains("sakje.do")) {
			int no = Integer.parseInt(no_);
			
			dto.setNo(no);
			
			dto = dao.getSelectOne(dto);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/guestBook/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sakjeProc.do")) {
			int no = Integer.parseInt(no_);
			String password = request.getParameter("password");
			
			dto.setNo(no);
			dto.setPassword(password);
			
			int result = 0;
			
			result = dao.setDelete(dto);
			
			if(result > 0) {
				String page = path + "/guestBook_servlet/list.do";
				response.sendRedirect(page);
			} else {
				String page = path + "/guestBook_servlet/sakje.do";
				response.sendRedirect(page);
			}
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
			
			String page = "/WEB-INF/guestBook/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
	}
}
