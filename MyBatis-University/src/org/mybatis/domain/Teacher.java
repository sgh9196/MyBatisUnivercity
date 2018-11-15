package org.mybatis.domain;

public class Teacher {
	
	private int number;				// Number
	private String id;					// 교수 ID
	private String pw;					// 교수 PassWord
	private String question;				// 질문
	private String answer1;				// 정답 1
	private String answer2;				// 정답 2
	private String answer3;				// 정답 3
	private String answer4;				// 정답 4
	private String answer5;				// 정답 5
	
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public String getPw() { return pw; }
	public void setPw(String pw) { this.pw = pw; }
	
	public String getQuestion() { return question; }
	public void setQuestion(String question) { this.question = question; }
	
	public String getAnswer1() { return answer1; }
	public void setAnswer1(String answer1) { this.answer1 = answer1; }
	
	public String getAnswer2() { return answer2; }
	public void setAnswer2(String answer2) { this.answer2 = answer2; }
	
	public String getAnswer3() { return answer3; }
	public void setAnswer3(String answer3) { this.answer3 = answer3; }
	
	public String getAnswer4() { return answer4; }
	public void setAnswer4(String answer4) { this.answer4 = answer4; }
	
	public String getAnswer5() { return answer5; }
	public void setAnswer5(String answer5) { this.answer5 = answer5; }
	
	@Override
	public String toString() {
		return "Teacher [number=" + number + ", id=" + id + ", pw=" + pw + ", question=" + question + ", answer1="
				+ answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + ", answer5="
				+ answer5 + "]";
	}
	
}
