package dto_dao;

import java.util.Scanner;

// 성적 처리
public class Score_Proc {
	Score_DAO dao = new Score_DAO();
	Scanner sc = new Scanner(System.in);
	
	//학생 이름, 국어, 영어, 수학 성적 입력하기
	
	public void insert() {
		
		//학생 번호
		int count = dao.studCount(); // 처음에는 0 반환
		
		System.out.println("입력 예 : 아무개 96 95 80");
		
		do {
			System.out.println((++count) + " 번 학생의 성적 입력 (종료:-1) --> ");
			
			//next(), nextline()
			String name = sc.next();
			
			if(name.equals("-1"))
				break;
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int math = sc.nextInt();
			
			Score_DTO dto = new Score_DTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMath(math);
			
			dao.setStudent(dto);
			
		}while(true);
	}
	
	// 모든 학생의 성적 출력하기
	public void printScore() {
		dao.sort();
		System.out.print("전체 학생 수 : " + dao.studCount() +" 명");
	
		if(dao.studCount() > 0 ) {
			System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
			System.out.println("==========================");
			
			for(Score_DTO dto : dao.getList()) {
				System.out.println(dto.getName() + "\t" + dto.getKor() + "\t" + dto.getEng() + "\t" + dto.getMath() + "\t" 
					   + dto.getTotal() + "\t" + dto.getAverage());
			}
		}
	}
	
	// 특정 학생의 성적 출력하기
	public void search(){
		System.out.println("학생의 이름을 입력하시오 >>");
		String name = sc.next();
		
		StringBuilder sb = new StringBuilder();
		
		for(Score_DTO dto : dao.getList(name)) {
			sb.append(dto.getName() + "\t" + dto.getKor() + "\t" + dto.getEng() + "\t" + dto.getMath() + "\t" 
					   + dto.getTotal() + "\t" + dto.getAverage());
		}
		
		if(sb.length() > 0) {
			System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
			System.out.println("==========================");
			
			System.out.println(sb.toString());
		}else {
			System.out.println(name + " (이)라는 학생은 없습니다.");
		}
	}
	
}
