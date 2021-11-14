package sungjuk.controller;

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

import member.model.dao.MemberDAO33;
import member.model.dto.MemberDTO;
import sungjuk.model.dao.SungjukDAO;
import sungjuk.model.dto.SungjukDTO;

@WebServlet("/sungjuk_servlet/*")
public class SungjukController extends HttpServlet {
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
		
		SungjukDAO dao = new SungjukDAO();
		SungjukDTO dto = new SungjukDTO();
		MemberDTO dtoM = new MemberDTO();
		MemberDAO33 daoM = new MemberDAO33();
		List<SungjukDTO> list = new ArrayList<>();
		List<MemberDTO> listM = new ArrayList<>();
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
				search_option = "id";
				search_data = "%";
			}
			
			if (search_option.equals("id") && !search_data.trim().equals("")) {
				search_option = "id";
			} else if (search_option.equals("name") && !search_data.trim().equals("")) {
				search_option = "name";
			} else if (search_option.equals("shihum_name") && !search_data.trim().equals("")) {
				search_option = "shihum_name";
			} else if (search_option.equals("grade") && !search_data.trim().equals("")) {
				search_option = "grade";
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
			
			String page = "/WEB-INF/sungjuk/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if(url.contains("view.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/sungjuk/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if(url.contains("chuga.do") == true) {
			listM = daoM.getSelectAll();
			
			request.setAttribute("listM", listM);
			
			String page = "/WEB-INF/sungjuk/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("chugaProc.do") == true) {
			String id = request.getParameter("id");
			String[] idAar = id.split("_");
			id = idAar[0];
			String name = idAar[1];
			
			String sihum_name = request.getParameter("sihum_name");
			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			String sci_ = request.getParameter("sci");
			String his_ = request.getParameter("his");
			
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			int sci = Integer.parseInt(sci_);
			int his = Integer.parseInt(his_);
			
			dto.setId(id);
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			
			int tot = dto.total();
			double avg = dto.average();
			
			String grade = "가";
			if(avg >= 90) {
				grade = "수";
			} else if(avg >= 80) {
				grade = "우";
			} else if(avg >= 70) {
				grade = "미";
			} else if(avg >= 60) {
				grade = "양";
			}
			
			if(sihum_name.equals("middle")) {
				sihum_name = "중간고사";
			} else {
				sihum_name = "기말고사";
			}
			
			dto.setSihum_name(sihum_name);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			int result = 0;
			result = dao.setInsert(dto);
			
			String msg = "";
			String move = "";
			
			if(result > 0) {
				msg = "등록되었습니다!";
				move = path + "/sungjuk_servlet/list.do";
			} else {
				msg = "등록에 실패했습니다!";
				move = path + "/sungjuk_servlet/chuga.do";
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
			
		} else if(url.contains("sujung.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/sungjuk/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sujungProc.do") == true) {
			String kor_ = request.getParameter("kor");
			String eng_ = request.getParameter("eng");
			String mat_ = request.getParameter("mat");
			String sci_ = request.getParameter("sci");
			String his_ = request.getParameter("his");
				
			int no = Integer.parseInt(no_);
			int kor = Integer.parseInt(kor_);
			int eng = Integer.parseInt(eng_);
			int mat = Integer.parseInt(mat_);
			int sci = Integer.parseInt(sci_);
			int his = Integer.parseInt(his_);

			int result = 0;
			
			dto.setNo(no);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			
			int tot = dto.total();
			double avg = dto.average();
			
			dto.setTot(tot);
			dto.setAvg(avg);
			
			String grade = "가";
			if(avg >= 90) {
				grade = "수";
			} else if(avg >= 80) {
				grade = "우";
			} else if(avg >= 70) {
				grade = "미";
			} else if(avg >= 60) {
				grade = "양";
			}
			
			dto.setGrade(grade);
		
			result = dao.setUpdate(dto);
			
			if(result > 0) {
				String page = path + "/sungjuk_servlet/view.do?no=" + dto.getNo();
				response.sendRedirect(page);
			} else {
				String page = path + "/sungjuk_servlet/sujung.do?no=" + dto.getNo();
				response.sendRedirect(page);
			}

		} else if(url.contains("sakje.do") == true) {
			int no = Integer.parseInt(no_);
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/sungjuk/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("sakjeProc.do") == true) {
			int no = Integer.parseInt(no_);
			
			int result = dao.setDelete(no);
			
			if(result > 0) {
				String page = path + "/sungjuk_servlet/list.do";
				response.sendRedirect(page);
			} else {
				String page = path + "/sungjuk_servlet/sakje.do";
				response.sendRedirect(page);
			}
		}
	}
	
}
