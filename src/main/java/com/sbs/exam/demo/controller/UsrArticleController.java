package com.sbs.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.ut.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;


@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;//서비스 import해주기..
	
	//액션메서드
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData <Article> doAdd(HttpSession httpSession, String title,String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "title을(를) 입력해주세요.");
		}
		
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "body을(를) 입력해주세요.");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId,title,body);//서비스.
		int id = writeArticleRd.getData1();
		Article article = articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd,"article",article);
	}
	
	@RequestMapping("/usr/article/list")
	//Model Spring 인터페이스
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles",articles);
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData <Article> getArticle(int id) {
		//Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.",id));//String
		}
		return ResultData.from("S-1",Ut.f("%d번 게시물입니다.", id),"article",article);//객체
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.",id));
		}
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2","게시물에 대한 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1",Ut.f("%d번 게시물을 삭제했습니다.", id),"id",id);
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(HttpSession httpSession, int id,String title,String body) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticle(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.",id));
		}
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2","게시물에 대한 권한이 없습니다.");
		}
		
		return articleService.modifyArticle(id,title,body);
	}
	


	//액션메서드끝
}	


