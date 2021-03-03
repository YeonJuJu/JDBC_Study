package dto_dao;

// DTO - Data Transfer Object (VO : Value Object)

public class Score_DTO {
	// DTO 에서 멤버변수 -> DB에서 table의 column
	// DTO 객체 하나 -> DB에서 table의 record 하나
	// DTO 를 모아놓은 Collection(ArrayList)
	// -> DB 에서 table
	
	private int kor, eng, math;
	private int total;
	private float average;
	private String name;
	
	public float getAverage() {
		average = getTotal() / 3.0F;
		return average;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		total = kor + eng + math;
		return total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
