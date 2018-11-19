package driving.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.mybatis.domain.Gride;
import org.mybatis.domain.Teacher;

public class UserInfo extends Plablum {

	private Scanner sc;

	private HashMap<Integer, Object> results = null;
	private ArrayList<Object> arrTeacher;
	private Teacher teacher;
	
	public UserInfo() {
		sc = new Scanner(System.in);
	}

	public int teacherRandIndex(int count, double p, int sum) {
		return (new Random().nextInt((int) (count * p)) + sum)+1;
	}

	public boolean teacherOverLapCheck(ArrayList<Object> arrTeacher, int rnd) {

		boolean tmp = false;

		for (int itr = 0; itr < arrTeacher.size(); itr++) {
			
			Teacher teacher = (Teacher) arrTeacher.get(itr);
			
			if(teacher.getNumber()== rnd) {
				tmp = true;
			}
			
		}

		return tmp;

	}

	/* User 로그인 */
	public String userSignUp() {

		Random rd = new Random();
		SQLMapper sqlMapper = new SQLMapper();

		int count = 0;
		double range = 0.0;
		int start = 0;
		
		results = sqlMapper.sqlTeacherSelect(results);
		count = sqlMapper.sqlTeacherCount();
		
		arrTeacher = new ArrayList<Object>(); 
		
		Gride gride = new Gride();
		
		System.out.print("Name >> "); gride.setStudent(sc.next());
		
		for (int itr = 0; itr < 6; itr++) {
			
			range = (itr>3) ? 0.2 : 0.4;
			start = (itr!=0 && itr%2==0) ? start+=4 : start;
			
			int rnd = teacherRandIndex(count, range, start);
			
			Teacher teacher = (Teacher) results.get(rnd);
			
			if(teacherOverLapCheck(arrTeacher, rnd)) { itr = itr - 1; }
			else {
				
				arrTeacher.add(teacher);
				gride.setNumber(sqlMapper.sqlGrideCount()+1);
				gride.getTeacher().setId(teacher.getId());
				gride.getTeacher().setQuestion(teacher.getQuestion());
				sqlMapper.sqlGrideInsert(gride);
				
			}
			
		}
		
		return gride.getStudent();
		
		//mapPrint();

	}
	
	public ArrayList<Object> getArray() {
		return arrTeacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Override
	public String getQuestion() {
		
		System.out.print(teacher.getQuestion() + " >> ");
		return null;
		
	}

	@Override
	public String getAnswer() {
		
		String answer = "";
		
		answer = sc.next();
		
		return answer;
		
	}

	public void mapPrint() {

		Iterator<Integer> itr = results.keySet().iterator();

		while (itr.hasNext()) {
			int key = itr.next();
			print(results.get(key) + "\n");
		}

	}

	public void print(String msg) {
		System.out.print(msg);
	}

}
