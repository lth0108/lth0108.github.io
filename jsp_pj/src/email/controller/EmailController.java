package email.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import chart.service.ChartService;
import email.model.dto.EmailDTO;
import email.service.EmailService;

@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
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
				
		if(url.contains("chuga.do")) {
			String page = "/WEB-INF/email/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do")) {
			String fromName = request.getParameter("fromName");
			String fromEmail = request.getParameter("fromEmail");
			String toEmail = request.getParameter("toEmail");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			EmailDTO dto = new EmailDTO();
			dto.setFromName(fromName);
			dto.setFromEmail(fromEmail);
			dto.setToEmail(toEmail);
			dto.setSubject(subject);
			dto.setContent(content);
			
			EmailService service = new EmailService();
			try {
				service.mailSender(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String page = "/WEB-INF/email/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
	}
}
