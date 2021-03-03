package dto_dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// DAO : Data Access Object
// DB에 접근하는 객체 - DB 관련 작업 
// Collection에 DATA를 입출력

public class Score_DAO {
	private ArrayList<Score_DTO> list = new ArrayList<>();
	
	//학생 수 구하기 : list.size()
	public int studCount() {
		return list.size();
	}
	
	//학생(Score_DTO)을 ArrayList에 add하기 : 학생 추가
	public void setStudent(Score_DTO dto) {
		list.add(dto);
	}
	
	//ArrayList에 저장된 모든 학생 반환
	public ArrayList<Score_DTO> getList(){
		return list;
	}
	
	//ArrayList에 저장된 특정 학생 반환
	 public ArrayList<Score_DTO> getList(String name){
		 // name에 해당하는 학생(record) 반환하기
		  	
		 ArrayList<Score_DTO> result = new ArrayList<>();
		 for(Score_DTO dto : this.list) {
			 if(dto.getName().equals(name)) {
			    result.add(dto);
			 }
		 }
		 return result;
    }
	
	//Score_DTO 객체의 이름을 기준으로 정렬하기
	public void sort() {
									     //익명클래스
		Comparator<Score_DTO> comp = new Comparator<Score_DTO>() {

			@Override
			public int compare(Score_DTO dto1, Score_DTO dto2) {
				return dto1.getName().compareTo(dto2.getName());
			}
			
		};
		
		Collections.sort(list, comp);
	}
}
