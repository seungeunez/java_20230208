package day8;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField; 

public class BookInsertFrame extends JDialog {
	private JTextField textField;	//제목
	private JTextField textField_1;	//저자
	private JTextField textField_2; //가격
	private JTextField textField_3;	//분류

	private JButton btnNewButton; //등록하기
	
	
	public BookInsertFrame() {
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(80, 65, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(80, 96, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 127, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(80, 158, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("등록하기"); //밖으로 뺐음 안나와 있길래	
		btnNewButton.setBounds(90, 204, 97, 23);
		getContentPane().add(btnNewButton);
	
		this.setSize(300, 400);	//사이즈 설정
		this.setVisible(true); 
		
	}
}
