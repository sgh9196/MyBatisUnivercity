package org.mybatis.domain;

public class Gride {
	
	private int number;
	private Teacher teacher;
	private String student;
	private int score;
	private String rating;
	
	public Gride() {
		teacher = new Teacher();
	}
	
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }

	public Teacher getTeacher() { return teacher; }
	public void setTeacher(Teacher teacher) { this.teacher = teacher; }
	
	public String getStudent() { return student; }
	public void setStudent(String student) { this.student = student; }

	public int getScore() { return score; }
	public void setScore(int score) { this.score = score; }
	
	public String getRating() { return rating; }
	public void setRating(String rating) { this.rating = rating; }
	

	@Override
	public String toString() {
		return "Gride [number=" + number + ", teacher=" + teacher + ", student=" + student + ", score=" + score
				+ ", rating=" + rating + "]";
	}

}
