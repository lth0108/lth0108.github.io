package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import test.test07.model.dto.Test07DTO;
import test.test09.model.dto.Test09DTO;

@WebServlet("/test_servlet/*")
public class TestController extends HttpServlet {
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

		if(url.contains("test01.do") == true) {
			String page = "/WEB-INF/test/test01/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test01Proc.do") == true) {
			String name = request.getParameter("name");
			String jumin = request.getParameter("jumin");
			
			request.setAttribute("name1", name);
			request.setAttribute("jumin1", jumin);
			
			String page = "/WEB-INF/test/test01/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test02.do") == true) {
			String page = "/WEB-INF/test/test02/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test02Proc.do") == true) {
			String name = request.getParameter("name");
			String jumin = request.getParameter("jumin");
			String gender = request.getParameter("gender");
			
			request.setAttribute("name", name);
			request.setAttribute("jumin", jumin);
			request.setAttribute("gender", gender);
			
			String page = "/WEB-INF/test/test02/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test03.do") == true) {
			String page = "/WEB-INF/test/test03/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.contains("test03Proc.do") == true) {
				String name = request.getParameter("name");
				String jumin = request.getParameter("jumin");
				String gender = request.getParameter("gender");
				String age = request.getParameter("age");
				
				request.setAttribute("name", name);
				request.setAttribute("jumin", jumin);
				request.setAttribute("gender", gender);
				request.setAttribute("age", age);
				
				String page = "/WEB-INF/test/test03/exam01Result.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			
		} else if(url.contains("test04.do") == true) {
			String page = "/WEB-INF/test/test04/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test04Proc.do") == true) {
			String name = request.getParameter("name");
			String jumin = request.getParameter("jumin");
			
			String temp1 = jumin.substring(7,8);
			String temp2 = jumin.substring(0,2);
			int age = Integer.parseInt(temp2);
			
			if(temp1.equals("1") || temp1.equals("2")) {
				age = 1900 + age;
			} else {
				age = 2000 + age;
			}
			age = 2021 - age;
			
			String gender = "여자";
			if(temp1.equals("1") || temp1.equals("3")) {
				gender = "남자";
			}
			
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("jumin", jumin);
			request.setAttribute("age", age);
		
			String page = "/WEB-INF/test/test04/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test05.do") == true) {
			String page = "/WEB-INF/test/test05/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.contains("test05Proc.do") == true){
			String name = request.getParameter("name");
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
			int tot = kor + eng + mat + sci + his;
			double avg = tot / 5.0; 
			String grade = "가";
			
			if(avg >= 90) {
				grade = "수";
			} else if (avg >= 80) {
				grade = "우";
			} else if (avg >= 70) {
				grade = "미";
			} else if (avg >= 60) {
				grade = "양";
			}
			
			request.setAttribute("name", name);
			request.setAttribute("kor", kor);
			request.setAttribute("eng", eng);
			request.setAttribute("mat", mat);
			request.setAttribute("sci", sci);
			request.setAttribute("his", his);
			request.setAttribute("tot", tot);
			request.setAttribute("avg", avg);
			request.setAttribute("grade", grade);
			
			String page = "/WEB-INF/test/test05/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test06.do") == true) {
			String page = "/WEB-INF/test/test06/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test06Proc.do") == true) {
			String productBunryu = request.getParameter("productBunryu");
			String productName = request.getParameter("productName");
			String productPrice_ = request.getParameter("productPrice");
			String productSalePercent_ = request.getParameter("productSalePercent");
			String productSaleMoney_ = request.getParameter("productSaleMoney");
			String productCompany = request.getParameter("productCompany");
			
			int productPrice = Integer.parseInt(productPrice_);
			int productSalePercent = Integer.parseInt(productSalePercent_);
			int productSaleMoney = Integer.parseInt(productSaleMoney_);
			
			request.setAttribute("productBunryu", productBunryu);
			request.setAttribute("productName", productName);
			request.setAttribute("productPrice", productPrice);
			request.setAttribute("productSalePercent", productSalePercent);
			request.setAttribute("productSaleMoney", productSaleMoney);
			request.setAttribute("productCompany", productCompany);
			
			String page = "/WEB-INF/test/test06/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test07.do")) {
			String page = "/WEB-INF/test/test07/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test07Proc.do")) {
			String productBunryu = request.getParameter("productBunryu");
			String productName = request.getParameter("productName");
			String productPrice_ = request.getParameter("productPrice");
			String productSalePercent_ = request.getParameter("productSalePercent");
			String productSaleMoney_ = request.getParameter("productSaleMoney");
			String productCompany = request.getParameter("productCompany");
			
			int productPrice = Integer.parseInt(productPrice_);
			int productSalePercent = Integer.parseInt(productSalePercent_);
			int productSaleMoney = Integer.parseInt(productSaleMoney_);
			
			Test07DTO dto = new Test07DTO();
			dto.setProductBunryu(productBunryu);
			dto.setProductName(productName);
			dto.setProductPrice(productPrice);
			dto.setProductSalePercent(productSalePercent);
			dto.setProductSaleMoney(productSaleMoney);
			dto.setProductCompany(productCompany);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/test/test07/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test08.do") == true) {
			String page = "/WEB-INF/test/test08/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test08Proc.do") == true) {
			String productBunryu = request.getParameter("productBunryu");
			String productName = request.getParameter("productName");
			String productPrice_ = request.getParameter("productPrice");
			String productSalePercent_ = request.getParameter("productSalePercent");
			String productSaleMoney_ = request.getParameter("productSaleMoney");
			String productCompany = request.getParameter("productCompany");
			
			int productPrice = Integer.parseInt(productPrice_);
			int productSalePercent = Integer.parseInt(productSalePercent_);
			int productSaleMoney = Integer.parseInt(productSaleMoney_);
			
			Map<String, String> map = new HashMap<>();
			map.put("productBunryu", productBunryu);
			map.put("productName", productName);
			map.put("productPrice", productPrice + "");
			map.put("productSalePercent", productSalePercent + "");
			map.put("productSaleMoney", productSaleMoney + "");
			map.put("productCompany", productCompany);
			
			request.setAttribute("map", map);
			
			
			String page = "/WEB-INF/test/test08/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test09.do") == true) {
			String page = "/WEB-INF/test/test09/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.contains("test09Proc.do") == true) {
			String name = request.getParameter("name");
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
			
			int tot = kor + eng + mat + sci + his;
			double avg = tot / 5.0;
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
			
			Test09DTO dto = new Test09DTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			request.setAttribute("dto", dto);
			
			String page = "/WEB-INF/test/test09/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
		} else if(url.contains("test10.do") == true) {
			String page = "/WEB-INF/test/test10/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.contains("test10Proc.do") == true) {
			String name = request.getParameter("name");
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
			
			int tot = kor + eng + mat + sci + his;
			double avg = tot / 5.0;
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
			
			Test09DTO dto = new Test09DTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSci(sci);
			dto.setHis(his);
			dto.setTot(tot);
			dto.setAvg(avg);
			dto.setGrade(grade);
			
			Map<String, Test09DTO> map = new HashMap<>();
			map.put("dto", dto);
			
			//	[Map{DTO(값)}]
			
			request.setAttribute("map", map);
			
			String page = "/WEB-INF/test/test10/exam01Result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.contains("test11.do")) {
			String page = "/WEB-INF/test/test11/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.contains("test11Proc_______.do")) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/img";
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
			
			
			String filename1 = multi.getFilesystemName("0");
			String fileOrgName1 = multi.getOriginalFileName("0");
			String fileType1 = multi.getContentType("0");
			
			long file1Size1 = 0;
			String mimetype1 = "";
			
			java.io.File file1 = multi.getFile("0");
			if(file1 != null) {
				file1Size1 = file1.length();
				
				Tika tika1 = new Tika();
				mimetype1 = tika1.detect(file1);

			}
			
			long file1Size2 = 0;
			String mimetype2 = "";
			String filename2 = multi.getFilesystemName("1");
			String fileOrgName2 = multi.getOriginalFileName("1");
			String fileType2 = multi.getContentType("1");

			java.io.File file2 = multi.getFile("1");
			if(file2 != null) {
				file1Size2 = file2.length();
				
				Tika tika2 = new Tika();
				mimetype2 = tika2.detect(file2);
			}
			
			String filename3 = multi.getFilesystemName("2");
			String fileOrgName3 = multi.getOriginalFileName("2");
			String fileType3 = multi.getContentType("2");

			long file1Size3 = 0;
			String mimetype3 = "";
			java.io.File file3 = multi.getFile("2");
			if(file3 != null) {
				file1Size3 = file3.length();
				
				Tika tika3 = new Tika();
				mimetype3 = tika3.detect(file3);
			}
			
			System.out.println("file1Size1 : " + file1Size1);
			System.out.println("mimetype1 : " + mimetype1);
			
			System.out.println("file1Size2 : " + file1Size2);
			System.out.println("mimetype2 : " + mimetype2);
			
			System.out.println("file1Size3 : " + file1Size3);
			System.out.println("mimetype3 : " + mimetype3);
			
			if(filename1 == null || fileOrgName1 == null) {
				filename1 = "-";
				fileOrgName1 = "-";
			}
			
			if(filename2 == null || fileOrgName2 == null) {
				filename2 = "-";
				fileOrgName2 = "-";
			}
			
			if(filename3 == null || fileOrgName3 == null) {
				filename3 = "-";
				fileOrgName3 = "-";
			}
			
			System.out.println("fileType1 : " + fileType1);
			System.out.println("fileType2 : " + fileType2);
			System.out.println("fileType3 : " + fileType3);
			
			String name = multi.getParameter("name");
			String phone = multi.getParameter("phone");
			System.out.println("name : " + name);
			System.out.println("phone : " + phone);
			
		} else if(url.contains("test11Proc____.do")) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/img";
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
					 int temp = Integer.parseInt(formTagName);
					 array[temp] = fileOrgName + "," + fileName + "," + fileSize + "," + mimeType;
		            }
			}
			
			for(int i=0;i<array.length;i++) {
				System.out.println(array[i]);
			}
			
		 	
		} else if(url.contains("test11Proc.do")) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/img";
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
			
			for(int i=0;i<upload_counter;i++) {
				String formTagName = i + "";
				
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
					 array[i] = fileOrgName + "," + fileName + "," + fileSize + "," + mimeType;
		            }
			}
			
		} else if (url.contains("test12.do")) {
			String attach_path = "C:/Users/20.12.9/Documents/LTH/attach";
			String upload_path = attach_path + "/product_img";
			
			ArrayList<String> list = new ArrayList<>();
			
			java.io.File f1 = new java.io.File(upload_path);
			java.io.File[] array = f1.listFiles();
			
			for(int i=0;i<array.length;i++) {
				if(array[i].isFile()) {
					System.out.println("파일 : " + array[i].getPath());
				} else {
					System.out.println("폴더 : " + array[i].getPath());
				}
				list.add(array[i].getPath());
			}
			
			request.setAttribute("list", list);
			
			String page = "/WEB-INF/test/test12/exam01.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.contains("test12Proc.do")) {
			String filePath = request.getParameter("filePath");
			System.out.println("filePath : " + filePath);
			
			java.io.File f1 = new java.io.File(filePath);
			if(f1.exists()) {
				f1.delete();
			} else {
				System.out.println("파일이 존재하지 않습니다!"); 
			}
			response.sendRedirect(path + "/test_servlet/test12.do");
			
		} else {
			System.out.println("url : " + url);
			return;
		}
	}
}
