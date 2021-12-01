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

	public ResultData<Integer> writeArticle(int memberId,int boardId,String title, String body) {
		
		articleRepository.writeArticle(memberId,boardId,title,body);
		int id = articleRepository.getLastInsertId();
		return ResultData.from("S-1",Ut.f("%d번게시물이 생성되었습니다.",id), "id",id);
	}
	public List<Article> getArticles(int actorId, int boardId, String searchKeywordTypeCode, String searchKeyword, int itemsCountInApage, int page) {
		
		int limitStart = (page -1) * itemsCountInApage;
		//10개의 게시물만 가져오겠다.
		int limitTake = itemsCountInApage;
		//0 ~ 10 , 10 ~ 20 , 20 ~ 30 게시물 보이기
		//리포지터리로 보내
		List<Article> articles = articleRepository. getArticles(boardId,searchKeywordTypeCode,searchKeyword,limitStart,limitTake);
		
		return articles;
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
			article.setExtra__actorCanModify(true);
		}
		
	}
	
	

	public ResultData actorCanModifyRd(int actorId, Article article) {
		if(article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2", "해당 게시물에 대한 권한이 없습니다.");
		}
		return ResultData.from("S-1","해당 게시물수정이 가능합니다.");
	}
	
	public ResultData actorCanDeleteRd(int actorId, Article article) {
		if(article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2", "해당 게시물에 대한 권한이 없습니다.");
		}
		return ResultData.from("S-1","해당 게시물삭제가 가능합니다.");
	}

	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
		
		return articleRepository.getArticlesCount(boardId,searchKeywordTypeCode,searchKeyword);
	}

	public ResultData <Integer> increaseHitCount(int id) {
		int affectRowsCount = articleRepository.increaseHitCount(id);
		
		if(affectRowsCount == 0) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.","affectRowsCount",affectRowsCount);
		}
		
		return ResultData.from("S-1", "조회수가 증가 되었습니다.","affectRowsCount",affectRowsCount);
	}

	public int getArticleHitCount(int id) {
		
		return articleRepository.getArticleHitCount(id);
	}
	
	
}	


