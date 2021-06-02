package PhoneBookPack;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {

	public static void main(String[] args) {
		hi();
	}
	
	private static void hi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*************************************");
		System.out.println("*        전화번호 관리 프로그램         *");
		System.out.println("*************************************");
				
		while(true) {			
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("-------------------------------------");
			System.out.print(">메뉴번호: ");
			int num = scanner.nextInt();
			System.out.println();
						
			if (num == 1) {
				PhoneBookDAO dao = new PhoneBookDAOImpl();
				List<PhoneBookVO> list = dao.getList();				
				Iterator<PhoneBookVO> it = list.iterator();				
				System.out.println("<1.리스트>");				
				while(it.hasNext()) {
					PhoneBookVO item = it.next();
					System.out.printf("%d.\t%s\t%s\t%s%n",
							item.getId(),
							item.getName(),
							item.getHp(),
							item.getTel());
				}System.out.println();
				
			}else if(num == 2) {
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = scanner.next();
				System.out.print(">휴대전화: ");
				String pnum = scanner.next();
				System.out.print(">집전화: ");
				String hnum = scanner.next();				
				PhoneBookVO vo = new PhoneBookVO(null, name, pnum, hnum);
				PhoneBookDAO dao = new PhoneBookDAOImpl();				
				dao.insert(vo);				
				System.out.println();
				System.out.println("[등록되었습니다.]");
				System.out.println();		
				
			}else if(num == 3) {
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				int id = scanner.nextInt();
				System.out.println();				
				PhoneBookDAO dao = new PhoneBookDAOImpl();
				dao.delete(Long.valueOf(id));				
				System.out.println("[삭제되었습니다.]");
				System.out.println();	
				
			}else if(num == 4) {
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String searchKey = scanner.next();
				System.out.println();				
				PhoneBookDAO dao = new PhoneBookDAOImpl();
				List<PhoneBookVO> list = dao.search(searchKey);				
				Iterator<PhoneBookVO> it = list.iterator();				
				while(it.hasNext()) {
					PhoneBookVO vo = it.next();
					System.out.printf("%d.\t%s\t%s\t%s%n",
							vo.getId(),
							vo.getName(),
							vo.getHp(),
							vo.getTel());
				}System.out.println();
				
			}else if(num == 5){
				System.out.println();
				System.out.println("*************************************");
				System.out.println("*              감사합니다              *");
				System.out.println("*************************************");
				scanner.close();
				break;
				
			}else {
				System.out.println("[다시입력해주세요]");
				System.out.println();
			}			
		}		
	}
}
