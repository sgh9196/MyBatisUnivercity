package org.mybatis.domain;

public class G2 {
	
	private String student;
	private String name;
	private int score;
	private String rating;
	
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "G2 [student=" + student + ", name=" + name + ", score=" + score + ", rating=" + rating + "]";
	}
	
	
	
}
