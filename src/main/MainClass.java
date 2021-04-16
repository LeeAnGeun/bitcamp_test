package main;

import java.util.List;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

import dao.EmployeeDao;
import db.DBConnection;
import dto.EmployeeDto;

public class MainClass {

	public static void main(String[] args) {
		
		DBConnection.initConnection();
		Scanner sc = new Scanner(System.in);
		
		EmployeeDao dao = EmployeeDao.getInstance();
		
		String menu[] = {"1.추가 ", "2.삭제 ", "3.수정 ", "4.모두보기 ", "5.종료"};
		boolean b = true;
		
		while(b) {
			for (int i = 0; i < menu.length; i++) {
				System.out.print(menu[i]);
			}
			System.out.println();
			System.out.print("메뉴를 선택하세요 : ");
			int num = sc.nextInt();
			
			switch (num) {
			case 1:
				System.out.print("이름 : ");
				String name = sc.next();
				System.out.print("핸드폰 번호 : ");
				String phone = sc.next();
				System.out.print("이메일 : ");
				String email = sc.next();
				System.out.print("입사일 : ");
				String hiredate = sc.next();
				
				EmployeeDto dto = new EmployeeDto(name, phone, email, hiredate);
				boolean add = dao.addEmployee(dto);
				
				if(add) {
					System.out.println("사원추가에 성공하였습니다");
				}else {
					System.out.println("사원 추가에 실패하였습니다.");
				}
				
				break;
			case 2:
				System.out.print("삭제할 사원 번호 : ");
				int seq = sc.nextInt();
				
				boolean delete = dao.deleteEmployee(seq);
				
				if(delete) {
					System.out.println("사원삭제에 성공하였습니다.");
				}else {
					System.out.println("사원삭제에 실패하였습니다.");
				}
				break;
			case 3:
				System.out.print("수정할 사원 번호 : ");
				int seq2 = sc.nextInt();
				System.out.print("이메일 : ");
				String email2 = sc.next();
				System.out.print("입사일 : ");
				String hiredate2 = sc.next();
								
				boolean update = dao.updateEmployee(new EmployeeDto(seq2, email2, hiredate2));
				
				if(update) {
					System.out.println("사원수정에 성공하였습니다.");
				}else {
					System.out.println("사원수정에 실패하였습니다.");
				}
				
				break;
			case 4:
				// 사원 출력
				List<EmployeeDto> list = dao.getEmployeeList();
				
				for (int i = 0; i < list.size(); i++) {
					dto = list.get(i);
					System.out.println(i+1 + " 사원의 정보 : " + dto.toString());
				}
				break;
			case 5:
				System.out.println("시스템을 종료합니다.");
				b = false;
				break;
	
			}
		}
		
	}

}
