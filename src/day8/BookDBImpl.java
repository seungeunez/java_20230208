package day8;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;

public class BookDBImpl implements BookDB {

//	Config cfig = new Config();	=> static으로 만들었기 때문에 이렇게 만들 필요없음

	// 공통변수 생성자, 메소드 모두 접근 가능한 변수
	private MongoCollection<Document> sequence = null;
	private MongoCollection<Document> books = null;

	// 생성자
	public BookDBImpl() {
		try {
			// URL과 데이터베이스 명칭 클래스를 따로 만들어주고 가져오는 방식으로 했음 static으로 만들었기 때문에 클래스명.변수명 으로하면 된다
			// 자동으로 가져옴
			MongoDatabase db = MongoClients.create(Config.URL).getDatabase(Config.DBNAME);
			this.sequence = db.getCollection("sequence");
			this.books = db.getCollection("books");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 책등록
	@Override
	public int insertBook(Book book) {

		try {

			// 책번호, 등록일자
			Bson filter = Filters.eq("_id", "SEQ_BOOK_NO");
			Bson update = Updates.inc("idx", 1);
			Document doc = this.sequence.findOneAndUpdate(filter, update);

			Document doc1 = new Document();
			doc1.append("_id", doc.getInteger("idx"));
			doc1.append("title", book.getTitle());
			doc1.append("author", book.getAuthor());
			doc1.append("price", book.getPrice());
			doc1.append("cate", book.getCate());
			doc1.append("date", new Date());

			InsertOneResult result = this.books.insertOne(doc1);
			System.out.println(result);

			if (result.getInsertedId().asInt32().getValue() == doc.getInteger("idx")) {
				// _id 타입이 int로 쓰여서 asInt32
				return 1;
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	// 책 전체 조회
	@Override
	public List<Book> selectBookList() {
		try {
			// 반환 타입을 위한 빈 배열 객체 생성(비어 있음)
			List<Book> list = new ArrayList<Book>();

			//전체 조회는 this.books.find() 까지만
			FindIterable<Document> docs = this.books.find(); // find( )사이에 조건이 없으니깐 전체 출력임
			// docs의 값을 list로 다 복사하기
			for (Document doc : docs) { // 전체목록에서 하나를 복사해서 doc에 저장
				Book book = new Book();
				book.setNo(doc.getInteger("_id"));
				book.setTitle(doc.getString("title"));
				book.setAuthor(doc.getString("author"));
				book.setPrice(doc.getLong("price")); // Int Long 주의하자 이거땜에 오류났음
				book.setCate(doc.getString("cate").charAt(0)); // String => char
				book.setDate(doc.getDate("date"));

				list.add(book); // 반복 회수만큼 list에 추가하ㅣㄱ

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 데이터를 최소 21개 이상 추가하고 10개씩 조회해
	//
	// 책 10개씩 조회
	@Override
	public List<Book> selectBookListPage(int page) {
		List<Book> list = new ArrayList<>();
		try {

			Bson sort = Filters.eq("_id", -1); // 책번호를 기준으로 내림차순함
			// page = 1 => 점프안함 page = 2 => 10개 점프하고 생기고, page = 3 =>20
			FindIterable<Document> docs = this.books.find().sort(sort).skip(10 * (page - 1)).limit(10);
										//조회 관련된건 this.books.find()뒤부터 달라질뿐 나머지 내용은 같다
			
			for (Document doc : docs) { // 전체목록에서 하나를 복사해서 doc에 저장
				Book book = new Book();
				book.setNo(doc.getInteger("_id"));
				book.setTitle(doc.getString("title"));
				book.setAuthor(doc.getString("author"));
				book.setPrice(doc.getLong("price")); 
				book.setCate(doc.getString("cate").charAt(0)); 
				book.setDate(doc.getDate("date"));

				list.add(book); // 반복 회수만큼 list에 추가하기

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	// 책 삭제
	@Override
	public int deleteBook(int no) {

		return 0;
	}

	// 책 수정
	@Override
	public int updateBook(Book book) {

//		try {
//			Bson filter = Filters.eq("_id", book.getNo());
//
//			Bson update1 = Updates.set("title", book.getTitle());
//			Bson update2 = Updates.set("author", book.getAuthor());
//			Bson update3 = Updates.set("title", book.getTitle());
//
//			Bson update = Updates.combine(update1, update2, update3);
//			return 1;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		}
		return -1;

	}

	// 책 1권 조회
	@Override
	public Book selectBookOne(int no) {

		return null;
	}

	// n이상 가격에 해당하는 책 조회
	@Override
	public List<Book> selectBookPrice(long price) {
		try {
			
		} catch (Exception e) {
			
		}
		

		return null;
	}

	// 분류에 해당하는 항목만 조회
	@Override
	public List<Book> selectBookCate(char cate) {

		return null;
	}

}
