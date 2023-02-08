package day8;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

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

		return null;
	}

	// 책 10개씩 조회
	@Override
	public List<Book> selectBookListPage(int page) {

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

		return 0;
	}

	// 책 1권 조회
	@Override
	public Book selectBookOne(int no) {

		return null;
	}

	// n이상 가격에 해당하는 책 조회
	@Override
	public List<Book> selectBookPrice(long price) {

		return null;
	}

	// 분류에 해당하는 항목만 조회
	@Override
	public List<Book> selectBookCate(char cate) {

		return null;
	}

}
