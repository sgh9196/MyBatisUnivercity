package driving.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.mybatis.domain.Gride;
import org.mybatis.domain.Teacher;

public class Manager extends Plablum {

	static Scanner sc;
	
	private Teacher teacher;
	
	// ==========================================================
	
	public Manager() {
		sc = new Scanner(System.in);
	}
	
	public Manager(Teacher teacher) {
		sc = new Scanner(System.in);
		this.teacher = teacher;
	}

	// ==========================================================
	
	/* 교수 Defalut Data */
	public void s_DefaultData() {
		
		SQLMapper sqlMapper = new SQLMapper();
		
		try {
		
			String[][] dataList = { {"1", "SGH", "1111", "O/X", "X", "O", "O", "X", "O"},
										{"2", "MGI", "2222", "(가위/바위/보)", "보", "바위", "보", "가위", "가위"},
										{"3", "NHW", "3333", "Number(1,2)", "1", "1", "2", "1", "2"},
										{"4", "AHG", "4444", "(짜장, 짬뽕)", "짜장", "짬뽕", "짜장", "짬뽕", "짜장"},
										{"5", "KWJ", "5555", "Animal(Tiger,Lion)", "Tiger", "Tiger", "Lion", "Lion", "Lion"},
										{"6", "KDH", "6666", "(결혼, 미혼)", "미혼", "미혼", "결혼", "결혼", "결혼"},
										{"7", "LDW", "7777", "Number(3,4)", "3", "4", "3", "3", "4"},
										{"8", "KCW", "8888", "(삼성, LG)", "LG", "LG", "LG", "LG", "LG"},
										{"9", "PJM", "9999", "Korea(South, North)", "South", "South", "South", "North", "North"},
										{"10", "BL", "0000", "(R,G,B)", "R", "G", "G", "B", "B"}
									};
			
			
			for(int iRow=0; iRow<dataList.length; iRow++) {
				
				Teacher teacher = new Teacher();
				
				teacher.setNumber(Integer.parseInt(dataList[iRow][0]));
				teacher.setId(dataList[iRow][1]);
				teacher.setPw(dataList[iRow][2]);
				teacher.setQuestion(dataList[iRow][3]);
				teacher.setAnswer1(dataList[iRow][4]);
				teacher.setAnswer2(dataList[iRow][5]);
				teacher.setAnswer3(dataList[iRow][6]);
				teacher.setAnswer4(dataList[iRow][7]);
				teacher.setAnswer5(dataList[iRow][8]);
				
				sqlMapper.sqlDefaultDataInsert(teacher);
				
			}
		} catch(Exception e) {
			
		}
	}
	
	/* 교수 회원 가입 */
	public Teacher s_SingUp() {

		SQLMapper sqlMapper = new SQLMapper();
		
		print("ID >> "); teacher.setId(sc.next());
		
		if(!s_idCheck(sqlMapper, teacher.getId())) {
			System.out.println("이미 등록 된 ID 입니다.");
			sqlMapper.transactionClose();
			return null;
		}
		
		
		teacher.setNumber(sqlMapper.sqlTeacherCount()+1);
		print("PW >> "); teacher.setPw(sc.next());
		teacher.setQuestion(getQuestion());
		teacher.setAnswer1(getAnswer());
		teacher.setAnswer2(getAnswer());
		teacher.setAnswer3(getAnswer());
		teacher.setAnswer4(getAnswer());
		teacher.setAnswer5(getAnswer());
		
		return teacher;
		
	}
	
	/* 교수 로그인 */
	public Teacher s_Login(Teacher login) {
		
		login = new Teacher();
		
		try {
			
			print("ID >> "); login.setId(sc.next());
			print("PassWord >> "); login.setPw(sc.next());
			
			login = new SQLMapper().sqlLoginCheck(login);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return login;
		
	}
	
	
	/* 교수 과목 듣는 사람 */
	public void s_studentList(String id) {
		
		HashMap<Integer, Object> results = null;
		
		SQLMapper sqlMapper = new SQLMapper();
		
		results = sqlMapper.sqlGrideSelect(results, id);
		
		Iterator<Integer> itr = results.keySet().iterator();

		while (itr.hasNext()) {
			
			int key = itr.next();
			
			Gride gride = (Gride) results.get(key);
			
			print("[" + gride.getNumber() + "] > " + gride.getStudent() + "\n");
			
		}
		
	}
	
	public void scoreList(String id) {
		
		HashMap<Integer, Object> results = null;
		SQLMapper sqlMapper = new SQLMapper();
		
		results = sqlMapper.sqlGrideSelect(results, id);
		
		Iterator<Integer> itr = results.keySet().iterator();

		ArrayList<Gride> grideList = new ArrayList<Gride>();
		
		while (itr.hasNext()) {
			int key = itr.next();
			grideList.add((Gride) results.get(key));
		}
		
		for(int i=0; i<grideList.size(); i++) {
			int count = (grideList.size()+1);
			
			for(int k=0; k<grideList.size(); k++) {
				if(grideList.get(i).getScore() >= grideList.get(k).getScore()) {
					count = count - 1;
				}
			}
			print("[" + grideList.get(i).getStudent() + "] 학생 [" + count + "]등\n");
		}
		
	}
	
	/* 교수 ID 확인 */
	public boolean s_idCheck(SQLMapper sqlMapper, String id) {
		
		return (sqlMapper.sqlIDCheck(id)==null) ? true:false;
		
	}
	
		
	public void loginQuestion(Teacher teacher) {
		
		print("\n" + teacher.getId() + "님의 문제 >> " + teacher.getQuestion() + "   |   ");
		print(teacher.getAnswer1() + "   ");
		print(teacher.getAnswer2() + "   ");
		print(teacher.getAnswer3() + "   ");
		print(teacher.getAnswer4() + "   ");
		print(teacher.getAnswer5() + "\n\n");
		
	}
	
	@Override
	public String getQuestion() {
		
		String question = "";
		
		print("Question >> "); question = sc.next();
		
		return question;
		
	}

	@Override
	public String getAnswer() {
		
		String answer = "";
		
		print("Answer >> "); answer = sc.next();
		
		return answer;
		
	}

	public static void print(String msg) {
		System.out.print(msg);
	}

}


