package day8;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class Book {
	
	
	private int no = 0;	//책번호
	private String title = ""; //책제목	
	private String author = ""; //저자
	private long price = 0L; //가격
	private char cate = 'A'; //분류 'A' 'B' 'C'
	private Date date = null; //등록일자
	

}
