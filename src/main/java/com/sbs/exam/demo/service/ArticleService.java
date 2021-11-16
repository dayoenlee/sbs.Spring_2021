package com.sbs.exam.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.ut.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Service//service 라고 달면 객체랑 연결이되어 컨트롤에 New 객체를 할 필요없어짐 컨트롤에@Autowired 쓰면 그 연결을 해줌
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public ArticleService() {
		
	}

	public ResultData<Integer> writeArticle(int memberId,String title, String body) {
		
		articleRepository.writeArticle(memberId,title,body);
		int id = articleRepository.getLastInsertId();
		return ResultData.from("S-1",Ut.f("%d번게시물이 생성되었습니다.",id), "id",id);
	}
	public List<Article> getArticles(int actorId) {
		List<Article> articles = articleRepository. getArticles();
		for(Article article : articles) {
			updatePrintForData(actorId,article);
		}
		return articleRepository. getArticles();
	}

	public Article getArticle(int actorId,int id) {
		Article article = articleRepository. getArticle(id);
		
		updatePrintForData(actorId,article);
	
		return article;
	}
	public void deleteArticle(int id) {
		
		articleRepository.deleteArticle(id);
	}
	public ResultData modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id,title,body);
		
		return ResultData.from("S-1","게시물이수정되었습니다.","article",id);
	}
	
	private void updatePrintForData(int actorId, Article article) {
		if(article == null) {
			return;
		}
		if(article.getMemberId() == actorId) {
			article.setExtra__actorCanDelete(true);
		}
		
	}
}	


