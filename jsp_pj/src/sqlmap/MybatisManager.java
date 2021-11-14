package sqlmap;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	
	private static SqlSessionFactory instance;
	
	private MybatisManager(){
		//Constructor
	}
	
	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
			String resource = "sqlmap/sqlMapConfig.xml";
			reader = Resources.getResourceAsReader(resource);
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) { reader.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}
