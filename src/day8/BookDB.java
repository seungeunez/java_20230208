package day8;

import java.util.List;

public interface BookDB {

	// 책등록
	public int insertBook(Book book);

	// 책 전체조회
	public List<Book> selectBookList(); // 전체조회라서 list씀

	// 책 10개씩 조회
	public List<Book> selectBookListPage(int page); // 얘도 여러개 조회하는거라서 list씀

	// 책 삭제
	public int deleteBook(int no);

	// 책 수정
	public int updateBook(Book book);

	// 책 1권 조회
	public Book selectBookOne(int no); // 딱하나만 꺼내는거니깐 Book을 소환

	// n이상 가격에 해당하는 책 조회
	public List<Book> selectBookPrice(long price);

	// 분류에 해당하는 항목만 조회
	public List<Book> selectBookCate(char cate);

}
