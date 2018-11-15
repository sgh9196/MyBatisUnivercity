package driving.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class UserInfo extends Plablum {

	private Scanner sc;
	
	private HashMap<Integer, Object> results = null;
	
	public UserInfo() {
		sc = new Scanner(System.in);
	}

	/* User 로그인 */
	public void userSignUp() {

		Random rd = new Random();
		SQLMapper sqlMapper = new SQLMapper();
		
		results = sqlMapper.sqlTeacherSelect(results);
		
		int count = sqlMapper.sqlTeacherCount();
		
		
		
		mapPrint();
		
	}

	@Override
	public String getQuestion() {
		return null;
	}

	@Override
	public String getAnswer() {
		return null;
	}
	
	public void mapPrint() {
		
		Iterator<Integer> itr = results.keySet().iterator();
		
		while(itr.hasNext()) {		
			int key = itr.next();
			print(results.get(key) + "\n");
		}
		
	}
	
	public void print(String msg) {
		System.out.print(msg);
	}

}
