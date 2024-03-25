import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DaumNews {
  public static void main(String[] args) {
//    다음 뉴스 페이지의 오늘의 이슈 제목 목록 가져오기
    System.out.println("Jsoup으로 다음 뉴스 페이지 데이터 가져오기");

//    Jsoup로 파싱하고자 하는 웹 사이트의 주소
    String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";

//    파싱된 데이터를 저장할 Document 객체
    Document html = null;

    try {
//      지정한 url에 접속하여 데이터 가져오기
      Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET).execute();
//      가져온 데이터를 Document 타입으로 변환하여 저장
      html = res.parse();
    }
    catch (IOException e) {
      System.out.println("Jsoup로 데이터 파싱 중 오류가 발생했습니다.");
      e.printStackTrace();
    }

//    오늘의 이슈 리스트 목록 가져오기
//    클래스 속성 값이 list_newsissue 인 태그 선택
//    현재 ul 태그를 가져옴
    Element list_newsissue = html.select(".list_newsissue").first();

//    ul 태그의 자식 태그인 li 태그를 모두 가져옴
//    Elements li_list = list_newsissue.select("li");
//    System.out.println("li의 수 : " + li_list.size());

//    ul 태그의 자식 태그인 li 태그의 자식 태그가 class 값이 item_issue 인 div 태그 하나 밖에 없으므로 ul 태그에서 div 태그를 바로 검색해서 모두 가져옴 (총 20개)
    Elements item_issue = list_newsissue.select(".item_issue");

//    Elements cont_thumb = item_issue.get(0).select(".cont_thumb");

//    Element strong = cont_thumb.select("strong").first();
//
//    Element a = strong.select(".link_txt").first();
//    String title = a.text();
//    System.out.println(title);

//    기사 제목이 들어있는 div 태그 목록을 반복문을 통하여 하나씩 출력
    for (int i = 0; i < item_issue.size(); i++) {
//      class 속성값이 item_issue 인 태그를 get()을 통해서 하나씩 가져옴
      Element item = item_issue.get(i);
//      item에서 class 속성값이 cont_thumb인 태그를 검색하여 출력
//      first()를 사용하여 가장 먼저 검색된 1개만 가져옴
//      class가 item_issue 인 태그 안에서 class 가 cont_thumb인 태그는 1개 밖에 없음
//      Element cont_thumb = item.select(".cont_thumb").first();
////      class가 cont_thumb인 태그 안에서 strong 태그 검색, cont_thumb 안에는 strong 태그가 1개 밖에 없음, first()를 사용하여 1개만 가져옴
//      Element strong = cont_thumb.select("strong").first();
////      strong 태그 안에는 class가 link_txt인 태그 검색, strong 태그 안에는 class 가 link_txt인 태그는 1개 밖에 없음, first()로 1개 가져옴
//      Element aTag = strong.select(".link_txt").first();
////      원하는 태그를 찾았으므로 원하는 데이터 가져오기
//      // 시작 태그와 끝 태그 사이의 문자열 가져오기
//      String title = aTag.text();
////      attr()을 사용하여 원하는 속성의 값 가져오기
//      String link = aTag.attr("href");
//      System.out.println("기사 제목 : " + title);
//      System.out.println("기사 링크 : " + link);

//      줄임버전
      Element newsATag = item.select(".link_txt").first();
      String newsTitle = newsATag.text();
      String newsLink = newsATag.attr("href");

      System.out.println("기사 제목 : " + newsTitle);
      System.out.println("기사 링크 : " + newsLink);

      System.out.println("----------------------\n");
    }
  }
}








