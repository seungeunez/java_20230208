package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import day8.Book;
import day8.BookDB;
import day8.BookDBImpl;

//인터페이스를 서로 공유하고 있기때문에 서로 버튼을 누르면 알고있는거임. 인터페이스를 안하면 안됨
public class BookInsertFrame extends JDialog implements ActionListener { // 1. actionlistenr가져옴 bookinsertframe에 오류가
																			// 생기니깐 override 하는거임
	private JTextField textField; // 제목
	private JTextField textField_1; // 저자
	private JTextField textField_2; // 가격
	private JTextField textField_3; // 분류
	private JButton btnNewButton; // 등록하기
	private BookDB bookDB = new BookDBImpl(); // 객체 생성 => DB접속, 2개 컬렉션 가져오기 수행
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	public BookInsertFrame() {
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(94, 89, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 120, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(94, 151, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(94, 182, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		btnNewButton = new JButton("등록하기"); // 밖으로 뺐음 안나와 있길래
		this.btnNewButton.addActionListener(this); // 2. 내 쪽으로 알려달라고 해주는거임
		btnNewButton.setBounds(106, 223, 97, 23);
		getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("책제목");
		lblNewLabel.setBounds(40, 89, 57, 15);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("저자");
		lblNewLabel_1.setBounds(39, 120, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("가격");
		lblNewLabel_2.setBounds(40, 151, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("분류");
		lblNewLabel_3.setBounds(39, 182, 57, 15);
		getContentPane().add(lblNewLabel_3);

		this.setSize(300, 400); // 사이즈 설정
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 여기는 버튼이 클릭될 때 실행되는 위치임

		System.out.println(e.getActionCommand()); // 3. 그럼 버튼을 눌렸을 때 얘가 실시간으로 작동하는거임
		// 버튼이 여러개일 경우 어떤 애가 작동될지 모르니깐 if문으로 어떤 버튼이 실행되야하는지 알려줘야함
		if (e.getActionCommand().equals("등록하기")) { // getActionCommand()이거 String임
			// 4개의 항목의 값을 가져와서 Book타입으로 DB에 저장하는 소스 구현해야함

			// 사용자가 입력한 4개의 항목 정보 가져오기
			String title = textField.getText();
			String author = textField_1.getText();
			String price = textField_2.getText();
			String cate = textField_3.getText();

			// Book타입으로 객체 생성
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setPrice(Long.parseLong(price));// 타입이 안맞으니깐 String => long으로 바꿔줘야함
			book.setCate(cate.charAt(0)); // String => char

			int ret = bookDB.insertBook(book);
			System.out.println(ret);

			if (ret == 1) {
				//JOptionPane 이용시 사용자 입력창, 확인창, 알림창 만들 수 있음
				//showMessageDialog 알림창 띄우는 함수
				JOptionPane.showMessageDialog(null, "책 등록 성공");	
				System.exit(0);//화면을 종료 시키기. 0이 정상종료임. 
			} else {
				JOptionPane.showMessageDialog(null, "책 등록 실패");
			}

		}

	}
}
