package com.sbs.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.ut.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;// 서비스 import해주기..

	// 액션메서드

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "title을(를) 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return ResultData.from("F-2", "body을(를) 입력해주세요.");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);// 서비스.
		int id = writeArticleRd.getData1();
		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	@RequestMapping("/usr/article/list")
	// Model Spring 인터페이스
	public String showList(HttpServletRequest req, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");
		List<Article> articles = articleService.getArticles(rq.getLoginedMemberId());

		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetal(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		// Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		// Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));// String
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);// 객체
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("게시물에 대한 권한이 없습니다.");
		}

		articleService.deleteArticle(id);
		return Ut.jsReplace(Ut.f("%d번 글 삭제했습니다.", id), "/usr/article/list");
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.historyBackOnView(Ut.f("%d번 게시물은 존재하지않습니다.", id));
		}

		ResultData actorCanModifyRd = articleService.actorCanModifyRd(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return rq.historyBackOnView(actorCanModifyRd.getMsg());
		}

		model.addAttribute("article", article);

		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("게시물에 대한 권한이 없습니다.");
		}

		articleService.modifyArticle(id, title, body);
		return Ut.jsReplace(Ut.f("%d번 게시물이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}

	// 액션메서드끝
}
