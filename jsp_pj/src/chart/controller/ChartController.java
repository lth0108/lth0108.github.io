package chart.controller;

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

@WebServlet("/chart_servlet/*")
public class ChartController extends HttpServlet {
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
				
		if(url.contains("index.do")) {
			String page = "/WEB-INF/chart/index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("piechart.do")) {
			String page = "/WEB-INF/chart/piechart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("piechart3d.do")) {
			String page = "/WEB-INF/chart/piechart3d.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("combochart.do")) {
			String page = "/WEB-INF/chart/combochart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("linechart.do")) {
			String page = "/WEB-INF/chart/linechart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("jsonFile.do")) {
			ChartService service = new ChartService();
			JSONObject json = service.getChartData();
			
			String chart_type = "ComboChart";
			
			request.setAttribute("chart_type", chart_type);
			request.setAttribute("chart_subject", "장바구니 상품별 챠트");
			request.setAttribute("chart_jsonData", json);
			
			String page = "/WEB-INF/chart/jsonFile.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
	}
}
