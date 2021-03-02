//Ex08_JDBC08
//DTO(Data Transfer Object) - 데이터 전송 객체
//VO (Value Object)
public class Member {
	private String id;
	private String author;
	private String title;
	private String content;
	
	public Member() {}
	
	public Member(String id, String author, String title, String content) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String toString() {
		return id+" == "+author+" == "+title+" == "+content;
	}
}
