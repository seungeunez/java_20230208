package frame;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import day8.Book;
import day8.BookDB;
import day8.BookDBImpl;

public class BookSelectListFrame extends JDialog {

	private BookDB bookDB = new BookDBImpl(); // DB연결 필요한 컬렉션 객체 생성 완료됨
	private static final long serialVersionUID = 1L;
	private JTable table;

	public BookSelectListFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));

		table = new JTable();

		// 인터페이스면 객체를 만들기 어렵다
		
		// Vector == ArraryList
		// Object[]
		String[] colume = { "번호", "제목", "저자", "가격", "분류", "날짜" }; // 배열로 만든것
//		Vector<String> vector = new Vector<>();	//벡터로 만든것 배열이나 벡터나 둘 중 하나 쓰면 됨 편한걸로
//		vector.add("번호");
//		vector.add("제목");
//		vector.add("저자");
//		vector.add("가격");
//		vector.add("분류");
//		vector.add("날짜");

		
		// DefaultTableModel model = new DefaultTableModel(vector, 0); // vector대신에
		// list로 생각할 것
		DefaultTableModel model = new DefaultTableModel(colume, 0);

		List<Book> list = bookDB.selectBookList();
		for (Book book : list) { // list에서 book으로 복사하는거임
			String[] data = { 	
					book.getNo() + "", // 문자열에 넣는거니깐 형변환을 해줘야함 그래서  +" "(문자)를 더해주면 바로 문자로 바뀜
					book.getTitle(), book.getAuthor(), 
					book.getPrice() + "", book.getCate() + "", book.getDate() + "",

			};
			model.addRow(data);

		}

		table.setModel(model); // 코딩을 여기서부터 짜고 위로 필요한것들 추가 했음 그런식으로 해야함

		JScrollPane jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);

		this.setSize(600, 500);
		this.setVisible(true);

	}

}
