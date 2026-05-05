# week10library
신입기수 박찬, 황예찬이 4주차 스터디 과제를 위해 만든 리포지토리

📚 도서관 관리 프로그램

🎯 핵심 기능
📝 도서 생성: 새로운 도서를 시스템에 추가

✏️ 도서 수정: 기존 도서의 정보 수정

🗑️ 도서 삭제: 도서 정보 삭제

🔍 도서 조회: 도서 정보 검색 및 전체 목록 조회


classs 데이터 구조 (사용되는 멤버들)
public class Book {

    // 멤버 변수
    private String title;    // 도서명
    private String author;   // 저자
    private String category; // 카테고리
    private int price;       // 가격
    private int year;        // 출간년도
}

// Book 객체를 ArrayList로 관리하는 CRUD 클래스 (사용되는 메서드는 아래 api 명세서에서 서술)
public class BookCRUD {
 private ArrayList<Book> bookList = new ArrayList<>();

}



📋 API 명세서

도서추가
public void addBook(Book book) 


도서 목록 출력
public void printAll() 


도서 정보 수정
public void updateBook(String title, String newAuthor, String newCategory, int newPrice, int newYear)


도서 삭제 - 도서명으로 찾아서 삭제
 public void deleteBook(String title) 


작업분담
viewer - 박찬

controller - 황예찬

model - 박찬 

main - 황예찬


 
