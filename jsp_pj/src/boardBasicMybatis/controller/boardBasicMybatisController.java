package boardBasicMybatis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boardBasicMybatis.model.dao.BoardBasicMybatisDAO;
import boardBasicMybatis.model.dto.BoardBasicMybatisDTO;

@WebServlet("/boardBasicMybatis_servlet/*")
public class boardBasicMybatisController extends HttpServlet {
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
		String page = "";
		
		if(url.contains("list.do")) {
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			List<BoardBasicMybatisDTO> list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			page = "/WEB-INF/boardBasicMybatis/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("view.do")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chuga.do")) {
			String no_ = request.getParameter("no");
			int no = 0;
			if(!(no_ == null || no_.trim().equals(""))) {
				no = Integer.parseInt(no_);
			}
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);

			page = "/WEB-INF/boardBasicMybatis/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do")) {
			String cookWriter = "-";
			HttpSession session = request.getSession();
			if(session.getAttribute("cookId") != null) {
				cookWriter = (String) session.getAttribute("cookId");
			}
			
			String no_ = request.getParameter("no");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			int no = 0;
			if(!(no_ == null || no_.trim().equals(""))) {
				no = Integer.parseInt(no_);
			}
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			int num = dao.getMaxNum() + 1;
			int ref = dao.getMaxRef() + 1;
			int re_step = 1;
			int re_level = 1;
			int hit = 0;
			
			if(no > 0) {
				BoardBasicMybatisDTO dto2 = dao.getSelectOne(no);
				dao.setUpdateReLevel(dto2);
				ref = dto2.getRef();
				re_step = dto2.getRe_step() + 1;
				re_level = dto2.getRe_level() + 1;

			}
			
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setWriter(cookWriter);
			dto.setNum(num);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setRef(ref);
			dto.setRe_step(re_step);
			dto.setRe_level(re_level);
			dto.setHit(hit);

			int result = dao.setInsert(dto);
			
			if(result > 0) {
				page = path + "/boardBasicMybatis_servlet/list.do";
				response.sendRedirect(page);
			} else {
				page = path + "/boardBasicMybatis_servlet/chuga.do";
				response.sendRedirect(page);
			}
			
			
		} else if(url.contains("sujung.do")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do")) {
			String no_ = request.getParameter("no");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			int no = Integer.parseInt(no_);
			
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
		
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			int result = dao.setUpdate(dto);
			
			if(result > 0) {
				page = path + "/boardBasicMybatis_servlet/view.do?no=" + no;
				response.sendRedirect(page);
			} else {
				page = path + "/boardBasicMybatis_servlet/sujung.do?no=" + no;
				response.sendRedirect(page);
			}
			
		} else if(url.contains("sakje.do")) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			BoardBasicMybatisDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/WEB-INF/boardBasicMybatis/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sakjeProc.do")) {
			String no_ = request.getParameter("no");
			String password = request.getParameter("password");
			int no = Integer.parseInt(no_);
			
			BoardBasicMybatisDTO dto = new BoardBasicMybatisDTO();
			dto.setNo(no);
			dto.setPassword(password);
			
			BoardBasicMybatisDAO dao = new BoardBasicMybatisDAO();
			int result = dao.setDelete(dto);
			
			if(result > 0) {
				page = path + "/boardBasicMybatis_servlet/list.do?no=" + no;
				response.sendRedirect(page);
			} else {
				page = path + "/boardBasicMybatis_servlet/view.do?no=" + no;
				response.sendRedirect(page);
			}
			
		}
		
		
	}
}
