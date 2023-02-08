package frame;

//설명필요

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import day8.Book;
import day8.BookDB;
import day8.BookDBImpl;

public class BookSelectListPageFrame extends JDialog implements ItemListener {

	private BookDB bookDB = new BookDBImpl();
	private JTable table = null;
	private JComboBox<String> combo;

	DefaultTableModel model = new DefaultTableModel();

	public BookSelectListPageFrame() {

		// 전체 레이아웃 BoardLayout
		getContentPane().setLayout(new BorderLayout(0, 0));

		// 중앙의 table위치
		table = new JTable();


		////////////////////////
		String[] colume = { "번호", "제목", "저자", "가격", "분류", "날짜" };	
		this.model = new DefaultTableModel(colume, 0); // 행이 빈칸 없이 시작하려면 0넣어야함 1을 넣으면 첫 행이 비어져 있더라
		List<Book> list = bookDB.selectBookListPage(1);	//초기화면을 selectBookListPage(page)로 하지말고 1로 설정해두면 1페이지부터 뜰수있게 해줌 
		for (Book book : list) {	//list를 book에 넣어서 
			String[] data = { book.getNo() + "", book.getTitle(), book.getAuthor(), book.getPrice() + "",
					book.getCate() + "", book.getDate() + "" };
			model.addRow(data);

		}
		table.setModel(model);
		////////////////////////////
		
		JScrollPane jScrollPane = new JScrollPane(table);	//스크롤 추가하는거 
		getContentPane().add(jScrollPane, BorderLayout.CENTER);	//표를 중앙에 배치해야 한번에 볼 수 있음
		//.add(page, BorderLayout.CENTER ) page로 하게된다면 잘려서 반드시 jScrollPane이 필요하다
		
		//borderLayout을 인터넷을 쳐보면 위치알 수 잇음
		

		// 위쪽의 페이지번호
		combo = new JComboBox<>();
		combo.addItem("1");
		combo.addItem("2");
		combo.addItem("3");
		combo.addItemListener(this);	// 버튼이나 누를수있는것들 클릭하면 this가 있는 클래스 지금 현재 이클래스의 정보를 보내주는거임 //this들은 this들이 있는 각 클래스를 말하는 거임
		getContentPane().add(combo, BorderLayout.NORTH);//원래 디자인하면서 생긴거

		this.setSize(600, 500);
		this.setVisible(true);

	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("aaa"+e.getStateChange());	//선택해제가 2 선택되면 1 내가 선택하면서 그전 상태가 해제가 되야 선택이 되니깐 해제가 되면서2로 나오고 선택되면 1로 나옴 
		//
		if (e.getStateChange() == ItemEvent.SELECTED) {	// 내가 선택하면 1로 되니깐 == 선택되면 1 선택안되면 2인데 
			int page = Integer.parseInt(combo.getSelectedItem().toString());	//getSelectedItem() 선택된 페이지를 page에 담고 //문자열에서 정수로 변환했음 페이지수라서??
			System.out.println(combo.getSelectedItem()); //페이지수 출력해줌

			String[] colume = { "번호", "제목", "저자", "가격", "분류", "날짜" };
			this.model = new DefaultTableModel(colume, 0);

			List<Book> list = bookDB.selectBookListPage(page);	//여기선 ()안에 page라고 해야함 반복해서 나오게? 1로 해두면 첫페이지값만 계속 나옴
			for (Book book : list) { //list는 목록인데 해당 page에 담길 내용을 저장해서 book에 또 담는거임
				String[] data = { book.getNo() + "", book.getTitle(), book.getAuthor(), book.getPrice() + "",
						book.getCate() + "", book.getDate() + "" };
				model.addRow(data); //행에 data를 한줄씩 추가하고

			}
			table.setModel(model);	//하나의 표로 합쳐지는? 그런 뜻인것같음 

		}

	}

}
