package frame;

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
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		JScrollPane jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);

		// 위쪽의 페이지번호
		combo = new JComboBox<>();
		combo.addItem("1");
		combo.addItem("2");
		combo.addItem("3");
		combo.addItemListener(this);
		getContentPane().add(combo, BorderLayout.NORTH);

		this.setSize(600, 500);
		this.setVisible(true);

	}
	//^_^.... ..........모르겠당 ! ~

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int page = Integer.parseInt(combo.getSelectedItem().toString());

			String[] colume = { "번호", "제목", "저자", "가격", "분류", "날짜" };
			this.model = new DefaultTableModel(colume, 0);

			List<Book> list = bookDB.selectBookListPage(page);
			for (Book book : list) {
				String[] data = { book.getNo() + "", book.getTitle(), book.getAuthor(), book.getPrice() + "",
						book.getCate() + "", book.getDate() + "" };
				model.addRow(data);

			}
			table.setModel(model);

		}

	}

}