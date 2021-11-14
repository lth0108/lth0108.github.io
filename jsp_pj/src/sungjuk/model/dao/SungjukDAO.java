package sungjuk.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardBasicMybatis.model.dto.BoardBasicMybatisDTO;
import config.DB;
import sqlmap.MybatisManager;
import sungjuk.model.dto.SungjukDTO;

public class SungjukDAO {
	String table_sungjuk = "sungjuk";
	String table_member = "member";

	
	//목록 join
	public List<SungjukDTO> getSelectAll(String search_option, String search_data, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_sungjuk", table_sungjuk);
		map.put("table_member", table_member);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SungjukDTO> list = session.selectList("sungjuk.getSelectAll", map);
		session.close();
		return list;
	}
	
	
	//totalRecord
		public int getTotalRecord(String search_option, String search_data) {
			Map<String, String> map = new HashMap<>();
			map.put("table_sungjuk", table_sungjuk);
			map.put("search_option", search_option);
			map.put("search_data", search_data);
			
			SqlSession session = MybatisManager.getInstance().openSession();
			int result = (Integer) session.selectOne("sungjuk.getTotalRecord", map);
			session.close();
			return result;
		}
	

	//뷰
	public SungjukDTO getSelectOne(int no) {
		Map<String, String> map = new HashMap<>();
		map.put("no", no + "");
		map.put("table_sungjuk", table_sungjuk);
		map.put("table_member", table_member);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		SungjukDTO dto = session.selectOne("sungjuk.getSelectOne", map);
		session.close();
		return dto;
	}

	
	//등록
	public int setInsert(SungjukDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_sungjuk", table_sungjuk);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("sungjuk.setInsert", map);
		session.commit();
		session.close();
		return result;
	}
	
	
	//수정
	public int setUpdate(SungjukDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_sungjuk", table_sungjuk);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("sungjuk.setUpdate", map);
		session.commit();
		session.close();
		return result;
	}

	
	//삭제
	public int setDelete(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_sungjuk", table_sungjuk);
		map.put("no", no + "");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("sungjuk.setDelete", map);
		session.commit();
		session.close();
		return result;
	}
}
