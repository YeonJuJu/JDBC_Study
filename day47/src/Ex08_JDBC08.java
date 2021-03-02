
public class Ex08_JDBC08 {
	public static void main(String[] args) {
		Member member = new Member("tjoeun1", "이순신", "난중일기", "한산대첩");
		System.out.println(member.toString());
		
		Member member2 = new Member();
		member2.setId("tjoeun2");
		member2.setAuthor("김좌진");
		member2.setTitle("독립운동");
		member2.setContent("청산리대첩");
		System.out.println(member2.toString());
		
		Member member3 = new Member("tjoeun3", "양만춘", "민족항쟁", "안시성전투");
		System.out.println(member3.getId() + " == " + member3.getAuthor()+ " == " + member3.getTitle()+ " == " + member3.getContent());
		
	}
}
