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
	private ArrayList<Object> aryGride;
	
	private Teacher teacher;
	
	public UserInfo() {
		sc = new Scanner(System.in);
	}

	public int teacherRandIndex(int count, double p, int sum) {
		return (new Random().nextInt((int) (count * p)) + sum)+1;
	}

	public boolean teacherOverLapCheck(int rnd) {

		boolean tmp = false;

		for(int itr=0; itr<aryGride.size(); itr++) {
			
			Gride gride = (Gride) aryGride.get(itr);
			
			if(gride.getTeacher().getNumber() == rnd) {
				tmp = true;
			}
			
		}

		return tmp;

	}

	/* User 로그인 */
	public void userSignUp() {

		Random rd = new Random();
		SQLMapper sqlMapper = new SQLMapper();
		
		String student = "";
		
		int count = 0;
		double range = 0.0;
		int start = 0;
		
		results = sqlMapper.sqlTeacherSelect(results);
		count = sqlMapper.sqlTeacherCount();
		
		aryGride = new ArrayList<Object>(); 
		
		System.out.print("Name >> "); student = sc.next();
		
		for (int itr = 0; itr < 6; itr++) {
			
			range = (itr>3) ? 0.2 : 0.4;
			start = (itr!=0 && itr%2==0) ? start+=4 : start;
			
			int rnd = teacherRandIndex(count, range, start);
			
			if(teacherOverLapCheck(rnd)) { itr = itr - 1; }
			else {
				
				Teacher teacher = (Teacher) results.get(rnd);
				
				Gride gride = new Gride();
				
				gride.setNumber(sqlMapper.sqlGrideCount()+1);
				gride.setStudent(student);
				gride.setTeacher(teacher);
				
				aryGride.add(gride);
				
				sqlMapper.sqlGrideInsert(gride);
				
			}
			
		}
		//mapPrint();
	}
	
	/* 문제 풀이 */
	public void problemSolving() {
		
		SQLMapper sqlMapper = new SQLMapper();
		
		for(int itr=0; itr<aryGride.size(); itr++) {
			
			Gride gride = (Gride) aryGride.get(itr);
			
			// gride 목록에 있는 Teacher 객체를 하나씩 보내줌
			this.teacher =gride.getTeacher();
			
			// 문제 출력
			getQuestion();
			
			// 문제 풀이
			getAnswer();
		
			gride.setScore(scoreCalculation(gride));
			gride.setRating(ratingCalculation(gride.getScore()));
			
			// SQL Update
			sqlMapper.sqlGrideUpdate(gride);
			
		}
		// 나머지 A
		// 80미만 B
		// 60미만 C
	}
	
	/* 점수 계산 */
	public int scoreCalculation(Gride gride) {
		
		int score = 0;
		
		score = (gride.getTeacher().getAnswer1().equals("O")) ? 20:0;
		score += (gride.getTeacher().getAnswer2().equals("O")) ? 20:0;
		score += (gride.getTeacher().getAnswer3().equals("O")) ? 20:0;
		score += (gride.getTeacher().getAnswer4().equals("O")) ? 20:0;
		score += (gride.getTeacher().getAnswer5().equals("O")) ? 20:0;
		
		return score;
		
	}
	
	public String ratingCalculation(int score) {
		
		String rating = "";
		
		if(score < 60) { rating = "C"; }
		else if(score < 80){ rating = "B"; }
		else { rating = "A"; }
		
		return rating;
		
	}
	
	public ArrayList<Object> getArray() {
		return aryGride;
	}

	@Override
	public String getQuestion() {
		
		System.out.println(teacher.getQuestion() + " 문제를 시작합니다.");
		
		return null;
		
	}

	@Override
	public String getAnswer() {
		
		String answer = "";
		
		System.out.print("Answer 1 >> ");
		answer = (teacher.getAnswer1().equals(sc.next())) ? "O":"X";
		teacher.setAnswer1(answer);
		
		System.out.print("Answer 2 >> ");
		answer = (teacher.getAnswer2().equals(sc.next())) ? "O":"X";
		teacher.setAnswer2(answer);
		
		System.out.print("Answer 3 >> ");
		answer = (teacher.getAnswer3().equals(sc.next())) ? "O":"X";
		teacher.setAnswer3(answer);
		
		System.out.print("Answer 4 >> ");
		answer = (teacher.getAnswer4().equals(sc.next())) ? "O":"X";
		teacher.setAnswer4(answer);
		
		System.out.print("Answer 5 >> ");
		answer = (teacher.getAnswer5().equals(sc.next())) ? "O":"X";
		teacher.setAnswer5(answer);
		
		System.out.println(teacher.toString());
		
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
