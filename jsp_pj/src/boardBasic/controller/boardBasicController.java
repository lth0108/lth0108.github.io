package boardBasic.controller;

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

import boardBasic.model.dao.boardBasicDAO;
import boardBasic.model.dto.boardBasicDTO;
import member.model.dao.MemberDAO33;
import member.model.dto.MemberDTO;

@WebServlet("/boardBasic_servlet/*")
public class boardBasicController extends HttpServlet {
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
		
		boardBasicDAO dao = new boardBasicDAO();
		boardBasicDTO dto = new boardBasicDTO();
		ArrayList<boardBasicDTO> list = new ArrayList<>();
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
		String cookWriter = "";
		
		if(session.getAttribute("cookId") != null) {
			cookWriter = (String) session.getAttribute("cookId");
		}
		System.out.println("작성자 : " + cookWriter);
		
		
		if(url.contains("list.do")) {
			list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/boardBasic/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("view.do")) {
			int no = Integer.parseInt(no_);
			String no_reply_ = request.getParameter("no_reply");
			int no_reply = 0;
			if(no_reply_ != null) {
				no_reply = Integer.parseInt(no_reply_);
			}
			
			
			dao.setUpdateHit(no);
			dto = dao.getSelectOne(no);
			list = dao.getSelectComment(no);
			
			request.setAttribute("no_reply", no_reply);
			request.setAttribute("dto", dto);
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/boardBasic/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chuga.do")) {
			String page = "/WEB-INF/boardBasic/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do")) {
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordChk = request.getParameter("passwordChk");
			
			if(!password.equals(passwordChk)) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다!');");
				out.println("location.href='" + path + "/boardBasic_servlet/chuga.do';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
			
			dto.setWriter(cookWriter);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPassword(password);

			
			int result = 0;
			result = dao.setInsert(dto);
			
			String msg = "";
			String move = "";
			
			if(result > 0) {
				msg = "등록되었습니다!";
				move = path + "/boardBasic_servlet/list.do";
			} else {
				msg = "등록에 실패했습니다!";
				move = path + "/boardBasic_servlet/chuga.do";
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
			
		} else if(url.contains("commentProc.do")) {
			String comment = request.getParameter("comment");
			String email = request.getParameter("email");
			String reply_comment_text = request.getParameter("reply_comment_text");
			String reply_email = request.getParameter("reply_email");
			String no_reply_ = request.getParameter("no_reply");
			
			int no = Integer.parseInt(no_);
			int no_reply = 0;

			System.out.println("reply_comment_text : " + reply_comment_text);
			
			if(comment == null || comment.trim().equals("")) {
				no_reply = Integer.parseInt(no_reply_);
				dto.setNo(no_reply);
				System.out.println("no_reply : " + no_reply);
				dto.setContent(reply_comment_text);
				dto.setEmail(reply_email);
				dao.setUpdateRe_level(no_reply);
			} else {
				dto.setNo(no);
				dto.setContent(comment);
				dto.setEmail(email);
				dao.setUpdateRe_level(no);
			}
			
			dto.setWriter(cookWriter);
			dto.setSubject("1");
			dto.setPassword("1");
			
			int result = 0;
			
			result = dao.setInsertComment(dto);
			
			String msg = "";
			String move = "";
			
			if(result > 0) {
				msg = "등록되었습니다!";
				move = path + "/boardBasic_servlet/view.do?no=" + no;
			} else {
				msg = "등록에 실패했습니다!";
				move = path + "/boardBasic_servlet/view.do?no=" + no;
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
			
			
		} else if(url.contains("sujung.do")) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/boardBasic/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do")) {
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			if(!password.equals(dto.getPassword())) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다!');");
				out.println("location.href='" + path + "/boardBasic_servlet/sujung.do?no=" + no + "'");
				out.println("</script>");
				out.flush();
				out.close();
				return;
				
			}
			
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setNo(no);
		
			int result = dao.setUpdate(dto);
			
			if(result > 0) {
				String page = path + "/boardBasic_servlet/view.do?no=" + dto.getNo();
				response.sendRedirect(page);
			} else {
				String page = path + "/boardBasic_servlet/sujung.do?no=" + dto.getNo();
				response.sendRedirect(page);
			}
			
		} else if(url.contains("sakje.do")) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/boardBasic/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sakjeProc.do")) {
			String ref_ = request.getParameter("ref");
			int ref = Integer.parseInt(ref_);
			
			int result = dao.setDelete(ref);
			
			if(result > 0) {
				String page = path + "/boardBasic_servlet/list.do";
				response.sendRedirect(page);
			}
		}
		
		else {
			
		}
		
		
	}

}
