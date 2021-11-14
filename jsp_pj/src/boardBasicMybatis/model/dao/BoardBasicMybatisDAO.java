package boardBasicMybatis.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import boardBasicMybatis.model.dto.BoardBasicMybatisDTO;
import sqlmap.MybatisManager;

public class BoardBasicMybatisDAO {
	String table_name = "boardBasic"; 
	
	public List<BoardBasicMybatisDTO> getSelectAll() {
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<BoardBasicMybatisDTO> list = session.selectList("boardBasicMybatis.getSelectAll", map);
		session.close();
		return list;
	}
	
	public int getMaxNum() {
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("boardBasicMybatis.getMaxNum", map);
		session.close();
		return result;
	}
	
	public int getMaxRef() {
		Map<String, String> map = new HashMap<>();
		map.put("table_name", table_name);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("boardBasicMybatis.getMaxRef", map);
		session.close();
		return result;
	}
	
	public BoardBasicMybatisDTO getSelectOne(int no) {
		Map<String, String> map = new HashMap<>();
		map.put("no", no + "");
		map.put("table_name", table_name);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		BoardBasicMybatisDTO dto = session.selectOne("boardBasicMybatis.getSelectOne", map);
		session.close();
		return dto;
	}
	
	public int setInsert(BoardBasicMybatisDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("boardBasicMybatis.setInsert", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setUpdate(BoardBasicMybatisDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("boardBasicMybatis.setUpdate", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setDelete(BoardBasicMybatisDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("boardBasicMybatis.setDelete", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setUpdateReLevel(BoardBasicMybatisDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_name", table_name);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("boardBasicMybatis.setUpdateReLevel", map);
		session.commit();
		session.close();
		return result;
	}
}
