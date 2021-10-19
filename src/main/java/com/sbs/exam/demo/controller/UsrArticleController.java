package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	//인스턴스 변수
	private int articleLastId;
	private List<Article> articles;
	//생성자
	public UsrArticleController() {
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
	private Article getArticle(int id) {
		for(Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	private void deleteArticle(int id) {
		Article article = getArticle(id);
		
		articles.remove(article);
	}
	
	//서비스메서드 끝
	//액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) {
		Article article = writeArticle(title,body);
		
		
		return article;
	}
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		
		return articles;
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = getArticle(id);
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		deleteArticle(id);
		return id + "번 글이 삭제 되었습니다.";
	}
	//액션메서드끝
}	


