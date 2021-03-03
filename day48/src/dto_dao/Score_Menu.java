package dto_dao;

import java.util.Scanner;

public class Score_Menu {
	public static void main(String[] args) {
		//console 창에 메뉴를 출력함
		Scanner scanner = new Scanner(System.in);
		Score_Proc proc = new Score_Proc();
		
		do {
			System.out.println("================== 성 적 처 리 ==================");
			System.out.println("1. 성적입력\t2. 성적출력\t3. 이름검색\t");
			System.out.print("메뉴를 선택하시오 (종료:0) >> ");
			
			int num = scanner.nextInt();
			System.out.println();
			
			if(num == 0)
				break;
			
			switch(num) {
			case 1:
				proc.insert();
				break;
			case 2:
				proc.printScore();
				break;
			case 3:
				proc.search();
				break;
			}
			
		}while(true);
		
		System.out.println("프로그램을 종료합니다.");
	}
}
