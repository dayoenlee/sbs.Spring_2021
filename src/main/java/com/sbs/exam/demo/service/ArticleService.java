package com.sbs.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.Article;

@Service//service 라고 달면 객체랑 연결이되어 컨트롤에 New 객체를 할 필요없어짐 컨트롤에@Autowired 쓰면 그 연결을 해줌
public class ArticleService {

	//인스턴스 변수
		private int articleLastId;
		private List<Article> articles;
		//생성자
		public ArticleService () {
			articleLastId =0;
			articles =new ArrayList<>();
			
			makeTestData();
			
		}
		//서비스메서드
		private void makeTestData() {
			for(int i =1; i <=10; i ++) {
				String title ="제목"+ i;
				String body ="내용"+ i;
				writeArticle(title,body);
			}
			
		}
		
		public Article writeArticle(String title,String body) {
			int id =articleLastId +1;
			Article article = new Article (id,title,body);
			
			articles.add(article);
			articleLastId = id;
			
			return article;
		}
		public  Article getArticle(int id) {
			for(Article article : articles) {
				if (article.getId() == id) {
					return article;
				}
			}
			return null;
		}
		public  void deleteArticle(int id) {
			Article article = getArticle(id);
			
			articles.remove(article);
		}
		//DAO
		public void modifyArticle(int id,String title,String body) {
			
			Article article = getArticle(id);
		
			article.setTitle(title);
			article.setBody(body);
		}
		//
		public List<Article> getArticles(){
			
			return articles;
		}
		
		//서비스메서드 끝
}	


