package memo.model.dao;

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
import memo.model.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {
	String table_memo = "memo";
	
	
	//목록
	public List<MemoDTO> getSelectAll(String search_option, String search_data, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_memo", table_memo);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getSelectAll", map);
		session.close();
		return list;
	}
	
	
	//totalRecord
	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("table_memo", table_memo);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = (Integer) session.selectOne("memo.getTotalRecord", map);
		session.close();
		return result;
	}
		
	
	//등록
	public int setInsert(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_memo", table_memo);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.setInsert", map);
		session.commit();
		session.close();
		return result;
	}
	

	//수정
	public int setUpdate(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_memo", table_memo);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setUpdate", map);
		session.commit();
		session.close();
		return result;
	}
	

	//삭제
	public int setDelete(int no) {
		Map<String, String> map = new HashMap<>();
		map.put("table_memo", table_memo);
		map.put("no", no + "");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("memo.setDelete", map);
		session.commit();
		session.close();
		return result;
	}
}
