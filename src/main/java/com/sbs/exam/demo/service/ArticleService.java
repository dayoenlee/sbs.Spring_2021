package com.sbs.exam.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;

@Service//service 라고 달면 객체랑 연결이되어 컨트롤에 New 객체를 할 필요없어짐 컨트롤에@Autowired 쓰면 그 연결을 해줌
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		
	}

	public int writeArticle(String title, String body) {
		
		articleRepository.writeArticle(title,body);
		return articleRepository.getLastInsertId();
	}
	public List<Article> getArticles() {
		
		return articleRepository. getArticles();
	}
	public Article getArticle(int id) {
		
		return articleRepository.getArticle(id);
	}
	public void deleteArticle(int id) {
		
		articleRepository.deleteArticle(id);
	}
	public void modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id,title,body);
	}
	
}	


