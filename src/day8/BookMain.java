package day8;

import frame.BookInsertFrame;
import frame.BookSelectListFrame;
import frame.BookSelectListPageFrame;

//화면을 실행시키는 역할

public class BookMain {

	public static void main(String[] args) {
		//new BookInsertFrame();	//사용할게 아니라서 생성자명 선언하는건 안했음  BookInsertFrame bif = 이런거 말이야
		
		
		
		
		
//		new BookSelectListFrame();
//		new BookInsertFrame();
		new BookSelectListPageFrame();
		
//		책 정보 22개 추가하는건 for문 쓰면 된다
/*		BookDB bookDB = new BookDBImpl();
		
		for(int i=0; i<23;i++) {
			Book book = new Book();
			book.setTitle("제목"+i);
			book.setAuthor("저자"+i);
			book.setPrice(10000+i); 
			book.setCate('A'); 
			bookDB.insertBook(book);
			
		}
*/
	}

}
