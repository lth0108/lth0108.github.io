package member.controller;

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

import org.checkerframework.framework.qual.RequiresQualifier;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		System.out.println(url);

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		List<MemberDTO> list = new ArrayList<>();
		String no_ = request.getParameter("no");

//===================================================================		
		int cookNo = 0;
		HttpSession session = request.getSession();

		if (session.getAttribute("cookNo") != null) {
			cookNo = (Integer) session.getAttribute("cookNo");
		}

		if (cookNo == 0) {
			if (url.contains("login.do")) {

			} else if (url.contains("loginProc.do")) {

			} else if (url.contains("logout.do")) {

			} else {

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
		}
//===================================================================
		String[] grade = url.split("/");
		session.setAttribute("cookGrade", grade[5]);

		String cookId = "";
		String memberMenu = "";
		String viewId = "";

		if (session.getAttribute("cookNo") != null) {
			memberMenu = (String) session.getAttribute("cookGrade");
			cookId = (String) session.getAttribute("cookId");
			viewId = (String) session.getAttribute("viewId");
			System.out.println(memberMenu);
			System.out.println(cookId);
			System.out.println(viewId);
		}

		if (memberMenu.contains("sakje.do") || memberMenu.contains("sakjeProc.do")) {
			if (!cookId.equals("admin")) {
				String move = path + "/member_servlet/list.do";

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자 권한이 필요합니다!');");
				out.println("location.href='" + move + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
		} else if (memberMenu.contains("chuga.do") || memberMenu.contains("chugaProc.do")) {
			if (!cookId.equals("admin") && !cookId.equals("user")) {
				String move = path + "/member_servlet/list.do";

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('유저 이상의 권한이 필요합니다!');");
				out.println("location.href='" + move + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
		} else if (memberMenu.contains("sujung.do") || memberMenu.contains("sujung.do)")) {
			if (!cookId.equals("admin") && !cookId.equals(viewId)) {
				String move = path + "/member_servlet/list.do";

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('자신의 회원정보만 수정할 수 있습니다!');");
				out.println("location.href='" + move + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
		} else if (memberMenu.contains("list.do") || memberMenu.contains("view.do")) {
			if (cookId.equals("asd")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('권한이 부족합니다!');");
				out.println("location.href='" + path + "';");
				out.println("</script>");
				out.flush();
				out.close();
				return;
			}
		}
//===================================================================

		if (url.contains("list.do") == true) {
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
			
			list = dao.getSelectAll(search_option, search_data, startRecord, lastRecord);

			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("list", list);

			String page = "/WEB-INF/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("view.do") == true) {
			int no = Integer.parseInt(no_);

			dto = dao.getSelectOne(no);
			request.setAttribute("dto", dto);

			session.setAttribute("viewId", dto.getId());

			String page = "/WEB-INF/member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("chuga.do") == true) {
			String page = "/WEB-INF/member/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("chugaProc.do") == true) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String passwordChk = request.getParameter("passwordChk");
			String name = request.getParameter("name");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String grade_ = "SILVER";

			String phone = phone1 + "-" + phone2 + "-" + phone3;

			if (phone.equals("--")) {
				phone = "미입력";
			}

			if (email.equals("") || email == null) {
				email = "미입력";
			}

			if (address.equals("") || address == null) {
				address = "미입력";
			}

			if (!password.equals(passwordChk)) {
				String page = path + "/member_servlet/chuga.do";
				response.sendRedirect(page);
				return;
			}

			dto.setId(id);
			dto.setPassword(password);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(address);
			dto.setGrade(grade_);

			int result = 0;
			result = dao.setInsert(dto);

			String msg = "";
			String move = "";

			if (result > 0) {
				msg = "등록되었습니다!";
				move = path + "/member_servlet/list.do";
			} else {
				msg = "등록에 실패했습니다!";
				move = path + "/member_servlet/chuga.do";
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

		} else if (url.contains("sujung.do") == true) {
			int no = Integer.parseInt(no_);

			dto = dao.getSelectOne(no);
			String phone = dto.getPhone();

			if (!phone.equals("미입력")) {
				String[] arrayP = phone.split("-");
				String phone1 = arrayP[0];
				String phone2 = arrayP[1];
				String phone3 = arrayP[2];

				dto.setPhone1(phone1);
				dto.setPhone2(phone2);
				dto.setPhone3(phone3);
			} else {
				String phone1 = "";
				String phone2 = "";
				String phone3 = "";

				dto.setPhone1(phone1);
				dto.setPhone2(phone2);
				dto.setPhone3(phone3);
			}

			request.setAttribute("dto", dto);

			String page = "/WEB-INF/member/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("sujungProc.do") == true) {
			String name = request.getParameter("name");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String phone3 = request.getParameter("phone3");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			int no = Integer.parseInt(no_);
			String phone = phone1 + "-" + phone2 + "-" + phone3;
			int result = 0;

			dto.setNo(no);
			dto.setName(name);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(address);

			result = dao.setUpdate(dto);

			if (result > 0) {
				String page = path + "/member_servlet/view.do?no=" + dto.getNo();
				response.sendRedirect(page);
			} else {
				String page = path + "/member_servlet/sujung.do?no=" + dto.getNo();
				response.sendRedirect(page);
			}

		} else if (url.contains("sakje.do") == true) {
			int no = Integer.parseInt(no_);

			dto = dao.getSelectOne(no);

			request.setAttribute("dto", dto);

			String page = "/WEB-INF/member/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("sakjeProc.do") == true) {
			int no = Integer.parseInt(no_);

			dto.setNo(no);

			int result = dao.setDelete(dto);
			System.err.println(result);
			if (result > 0) {
				String page = path + "/member_servlet/list.do";
				response.sendRedirect(page);
			} else {
				String page = path + "/member_servlet/sakje.do?no=" + dto.getNo();
				response.sendRedirect(page);
			}
		} else if (url.contains("login.do")) {
			String page = "/WEB-INF/member/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.contains("loginProc.do")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			id = id.replace(" ", "");
			if (password.contains(" ")) {
				String move = path + "/member_servlet/login.do";

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

			dto.setId(id);
			dto.setPassword(password);

			dto = dao.getLogin(dto);

			if (dto.getNo() > 0) {
//				session = request.getSession();
				session.setAttribute("cookNo", dto.getNo());
				session.setAttribute("cookId", dto.getId());
				session.setAttribute("cookName", dto.getName());
				response.sendRedirect(path + "/member_servlet/list.do");
			} else {
				response.sendRedirect(path + "/member_servlet/login.do");
			}

		} else if (url.contains("logout.do")) {
			String page = "/WEB-INF/member/logout.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		}
	}
}
