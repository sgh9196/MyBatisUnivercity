package driving.main;

import java.util.Scanner;

import org.mybatis.domain.Teacher;

public class Executor {

	private static SQLMapper sqlMapper;
	private static Teacher login;
	
	private static Scanner sc;

	// ====================================================================================

	public static void info() {
		
		try {
			
			sqlMapper = new SQLMapper();
			sc = new Scanner(System.in);

		} catch (Exception e) {
			System.out.println("MyBatis Setting Err");
		}
		
	}

	/* 테이블 셋팅 */
	public static void setTable() {
		
		try {
			sqlMapper.creatTable();
		} catch (Exception e) {
			System.out.println("setTable Err -> " + e.getMessage());
		}
		
	}


	/* 교수 회원 가입 */
	public static void mSignUp() {

		int tmp = 0;
		
		Teacher teacher = new Teacher();
		Manager manager = new Manager(teacher);
		
		teacher = manager.s_SingUp();
		
		if(teacher!=null) {
			
			tmp = sqlMapper.sqlIdInsert(teacher);
			
			if(tmp==1) { print("교수 등록 '완료'\n"); }
			else { print("교수 등록 '취소'\n"); }
			
		}
		
	}
	
	/* 교수 Login */
	public static void mT_Login() {
		
		Manager manager = new Manager();
		
		login = manager.s_Login(login);
		
		if(login!=null) { 
			
			while(true) {
				
				System.out.print("1. 문제보기  2. 학생보기  3. 석차보기\n>> ");
				
				switch(sc.nextInt()) {
					case 1:
						manager.loginQuestion(login);
						break;
					case 2:
						manager.s_studentList(login.getId());
						break;
					case 3:
						manager.scoreList(login.getId());
						break;
					default:
						return;
				}
				
			}

		}
		else {
			print("\n\n!! 잘못 된 정보입니다. !!\n\n"); 
		} 
		
	}
	// 내가 낸 문제보기, 학생, 석차보기
	public static void mS_Login() {
		
		UserInfo userInfo = new UserInfo();
		userInfo.userSignUp();
		
		print("\n시험을 시작합니다..\n\n");
		
		userInfo.problemSolving();
		
	}
	
	public static void main(String[] args) {
		
		info();
		setTable();
		
		Manager manager = new Manager();
		
		if(sqlMapper.sqlTeacherCount()==0) { manager.s_DefaultData(); }
		
		
		while(true) {
			
			print("1. 교수 회원가입   2. 교수 로그인  3. 학생\n>> ");
			
			switch(sc.nextInt()) {
				case 1:
					mSignUp();
					break;
				case 2:
					mT_Login();
					break;
				case 3:
					mS_Login();
					break;
				default:
					return;
			}
			
		}
		
	}
	
	
	/* 메시지 출력 */
	public static void print(String msg) {
		System.out.print(msg);
	}
	
	
}
