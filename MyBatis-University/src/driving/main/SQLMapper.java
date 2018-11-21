package driving.main;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.Gride;
import org.mybatis.domain.Teacher;

public class SQLMapper {

	private static final String resource = "resources/mybatis/config-mybatis.xml";
	private static final String parameter = "org.mybatis.persistence.";

	private static Connection connection;
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession;

	/* 마이바티스 셋팅 */
	public SQLMapper() {

		try {
			/* 마이바티스 설정 XML 파일 경로 */
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Connection connect() throws SQLException {
		
		try {
			
			if(connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/University?serverTimezone=UTC",
																	"sgh", "1234");
			}
			
			return connection;
			
		} catch(SQLException e) {
			throw e;
		}
		
	}
	
	/* 트랜잭션 Open */
	public void transactionOpen() {
		sqlSession = sqlSessionFactory.openSession();
	}

	/* 트랜잭션 Close */
	public void transactionClose() {
		sqlSession.close();
	}
	
	/* Commit */
	public int sqlCommit() {
		
		int slt = 0;
		
		System.out.print("<SUCCESS> 1. 확정  2. 취소 : ");
		
		slt = new Scanner(System.in).nextInt();
		if(slt==1) { sqlSession.commit(); }
		
		return slt;
		
	}

	/* 테이블 Setting */
	public void creatTable() {

		try {
			transactionOpen();
			sqlSession.update(parameter + "GrideMapper.createTableTeacher");
			sqlSession.update(parameter + "GrideMapper.createTableGride");
			transactionClose();
		} catch (Exception e) {
			System.out.println("createTable Err -> " + e.getMessage());
		}
	}
	
	/* 기본 데이터 Insert */
	public void sqlDefaultDataInsert(Teacher teacher) {
		
		try {
			
			transactionOpen();
			
			sqlSession.insert(parameter + "GrideMapper.teacherInsert", teacher);
			sqlSession.commit();
			
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 교수 ID 중복 체크 */
	public String sqlIDCheck(String id) {
		
		String tmp = "";
		
		try {
			
			transactionOpen();
			tmp = sqlSession.selectOne(parameter + "GrideMapper.idSelect", id);
			transactionClose();
			
		} catch(Exception e) {
			System.out.println("sqlIDCheck Err -> ");
		}
		
		return tmp;
		
	}
	
	/* 교수 Count */
	public int sqlTeacherCount() {
		
		int count = 0;
		
		try {
			
			transactionOpen();
			count = sqlSession.selectOne(parameter + "GrideMapper.countTeacher");
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	/* 통합 DB Count */
	public int sqlGrideCount() {
		
		int count = 0;
		
		try {
			
			transactionOpen();
			count = sqlSession.selectOne(parameter + "GrideMapper.countGride");
			transactionClose();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	/* 교수 등록*/
	public int sqlIdInsert(Teacher teacher) {
		
		int tmp = 0;
		
		try {
			
			transactionOpen();
			
			tmp = sqlSession.insert(parameter + "GrideMapper.teacherInsert", teacher);
			
			if(tmp == 1 && sqlCommit()==1) { tmp = 1; }
			else {  tmp = 0; }
			
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tmp;
		
	}
	
	/* 통합 DB 등록 */
	public int sqlGrideInsert(Gride gride) {
		
		int tmp = 0;
		
		try {
			
			transactionOpen();
			tmp = sqlSession.insert(parameter + "GrideMapper.grideInsert", gride);
			sqlSession.commit();
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tmp;
		
	}
	
	public void sqlGrideUpdate(Gride gride) {
		
		try {
			
			transactionOpen();			
			sqlSession.update(parameter + "GrideMapper.grideUpdate", gride);
			sqlSession.commit();
			transactionClose();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 교수 Login */
	public Teacher sqlLoginCheck(Teacher teacher) {
		
		
		try {
			
			transactionOpen();
			teacher = sqlSession.selectOne(parameter + "GrideMapper.loginSelect", teacher);
			transactionClose();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return teacher;
		
	}
	
	/* 랜덤 교수 선택 */
	public HashMap<Integer, Object> sqlTeacherSelect(HashMap<Integer, Object> results) {
		
		try {
			
			results = new HashMap<Integer, Object>();
			
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			
			preparedStatement = connect().prepareStatement("SELECT * FROM Teacher");
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			while(resultSet.next()) {
				
				Teacher teacher = new Teacher();
				
				teacher.setNumber(resultSet.getInt("Number"));
				teacher.setId(resultSet.getString("ID"));
				teacher.setQuestion(resultSet.getString("Question"));
				teacher.setAnswer1(resultSet.getString("Answer_1"));
				teacher.setAnswer2(resultSet.getString("Answer_2"));
				teacher.setAnswer3(resultSet.getString("Answer_3"));
				teacher.setAnswer4(resultSet.getString("Answer_4"));
				teacher.setAnswer5(resultSet.getString("Answer_5"));
				
				results.put(teacher.getNumber(), teacher);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return results;
		
	}
	
}
