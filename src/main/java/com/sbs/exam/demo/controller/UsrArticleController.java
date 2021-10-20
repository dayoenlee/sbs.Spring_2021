package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;//서비스 import해주기..
	
	//액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) {
		Article article = articleService.writeArticle(title,body);//서비스.
		
		
		return article;
	}
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		
		return articleService.getArticles();
	}
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		//Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return id +"번 게시물은 존재하지 않습니다.";//String
		}
		return article;//객체
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		articleService.deleteArticle(id);
		return id + "번 글이 삭제 되었습니다.";
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id,String title,String body) {
		Article article = articleService.getArticle(id);
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		articleService.modifyArticle(id,title,body);
		return id + "번 글이 수정 되었습니다.";
	}
	


	//액션메서드끝
}	


