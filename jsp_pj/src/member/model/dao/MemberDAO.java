package member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {
	String table_member = "member";
	
	
	//목록
	public List<MemberDTO> getSelectAll(String search_option, String search_data, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);

		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getSelectAll", map);
		session.close();
		return list;
	}
	
	
	//totalRecord
	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = (Integer) session.selectOne("member.getTotalRecord", map);
		session.close();
		return result;
	}
	
	
	//뷰
	public MemberDTO getSelectOne(int no) {
		Map<String, String> map = new HashMap<>();
		map.put("no", no + "");
		map.put("table_member", table_member);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO dto = session.selectOne("member.getSelectOne", map);
		session.close();
		return dto;
	}
	
	
	//등록
	public int setInsert(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("member.setInsert", map);
		session.commit();
		session.close();
		return result;
	}
	
	
	//등급수정
	public int setBuyUpdate(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.setBuyUpdate", map);
		session.commit();
		session.close();
		return result;
		
	}
	
	
	//수정
	public int setUpdate(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.setUpdate", map);
		session.commit();
		session.close();
		return result;
	}
	
	
	
	public int setDelete(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("dto", dto);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("member.setDelete", map);
		session.commit();
		session.close();
		return result;
	}

	
	//로그인
	public MemberDTO getLogin(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("table_member", table_member);
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("member.getLogin", map);
		session.close();
		return dto;
	}
}
