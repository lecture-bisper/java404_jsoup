import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DaumNewsTodaySerises {
  public static void main(String[] args) {
    System.out.println("\n다음 뉴스의 오늘의 연재 출력하기\n");

//    문제 1) jsoup을 사용하여 다음 뉴스의 오늘의 연재 부분을 파싱하여 화면에 출력하는 프로그램을 작성하세요
//    출력 형태 = 기사 제목, 기사 링크 2가지 내용을 출력하세요
//    실행 순서
//    1. url 설정
//    2. Document 객체 생성
//    3. Connection.Response 객체 생성 및 Jsoup.connect()로 지정한 url에 접속
//    4. 받아온 데이터를 Document 객체로 변환
//    5. 가져올 데이터가 있는 태그 중 가장 가까운 조상 태그 가져오기
//    6. select()를 사용하여 원하는 태그 가져오기
//    7. 마지막에 선택한 태그에서 기사 제목 및 기사 링크 가져오기

//    다음 뉴스 페이지 url 입력
    String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";

//    Jsoup 파싱 데이터를 받기 위한 객체 생성
    Document html = null;

    try {
//      지정한 웹 서버에 Jsoup을 통해서 접속 및 전체 데이터 가져오기
      Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET).execute();
//     전체 데이터 중 html 코드만 파싱하여 받기
      html = res.parse();
    }
    catch (IOException e) {
      System.out.println("Jsoup 사용 중 오류가 발생했습니다.");
      e.printStackTrace();
    }

////    클래스 값이 list_todayseries 인 태그를 선택
//    Element item = html.select(".list_todayseries").first();
//
////    클래스 값이 list_todayseries 인 태그에서 클래스 값이 item_todayseries 인 태그를 모두 검색
//    Elements itemList = item.select(".item_todayseries");
//    System.out.println(itemList.size());

//    전체 html에서 클래스 값이 item_todayseries 인 태그 모두 검색
    Elements items = html.select(".item_todayseries");

//    오늘의 연재에서 기사 제목을 가지고 있는 a 태그의 class 값이 link_txt 이므로 select()를 사용하여 link_txt를 검색
//    클래스 값이 item_todayseries인 태그 안에는 link_txt 라는 클래스 값을 가지고 있는 태그는 단 1개 뿐임, 해당 태그를 select()로 검색 후 Element 객체에 저장하면 됨
//    클래스 값이 item_todayseries인 태그는 개수가 8개의 리스트이며, 각각의 요소에 link_txt 클래스를 가고 있는 a 태그가 각각 1개씩 있음(link_txt)로 검색 시 8개가 나옴
    Elements aTagList = items.select(".link_txt");

//    반복문을 사용하여 link_txt을 가지고 있는 리스트에서 데이터를 출력
    for (Element item : aTagList) {
      String newsTitle = item.text();
      String newsLink = item.attr("href");
      System.out.println("기사 제목 : " + newsTitle);
      System.out.println("기사 링크 : " + newsLink);
      System.out.println("---------------\n");
    }
  }
}










