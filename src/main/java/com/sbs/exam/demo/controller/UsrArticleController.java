package com.sbs.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.service.BoardService;
import com.sbs.exam.demo.ut.Ut;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Board;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;// 서비스 import해주기..
	private BoardService boardService;
	private Rq rq;
	
	public UsrArticleController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}
	// 액션메서드

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId,String title, String body) {
		

		if (Ut.empty(title)) {
			return rq.jsHistoryBack("title을(를) 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body을(를) 입력해주세요.");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(),boardId, title, body);// 서비스.
		int id = writeArticleRd.getData1();

		return rq.jsReplace(Ut.f("%d번 게시물이 생성되었습니다.", id),Ut.f("../article/detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite(HttpServletRequest req, Model model) {

		
		return "usr/article/write";
	}


	@RequestMapping("/usr/article/list")
	// Model Spring 인터페이스
	//RequestParam = 기본 파라미터 지정
	public String showList(Model model,@RequestParam(defaultValue="title,body") String searchKeywordTypeCode,
			@RequestParam(defaultValue="") String searchKeyword,@RequestParam(defaultValue="1") int boardId,
			@RequestParam(defaultValue="1") int page) {
		
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			return rq.historyBackOnView(Ut.f("%d번 게시판은 존재하지 않습니다.",boardId));
		}
		int articlesCount = articleService.getArticlesCount(boardId,searchKeywordTypeCode,searchKeyword);
		// 한페이지에 10개게시물만
		int itemsCountInApage = 10;
		int pagesCount = (int)Math.ceil((double)articlesCount / itemsCountInApage);
		//글이 30개 / 10 = 3page
		//글 20개 10 * 2 / 글 21개 3page
		
		List<Article> articles = articleService.getArticles(rq.getLoginedMemberId(),boardId,searchKeywordTypeCode,searchKeyword,itemsCountInApage,page);
		
		model.addAttribute("board", board);
		model.addAttribute("boardId",boardId);
		model.addAttribute("articles", articles);
		model.addAttribute("pagesCount",pagesCount);
		model.addAttribute("articlesCount",articlesCount );
		model.addAttribute("page",page);
		
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetal(Model model, int id) {
		// Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		// Object 는 모든 리턴이 가능-> 좋은 코드는 아님 나중에 개선
		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));// String
		}
		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);// 객체
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("게시물에 대한 권한이 없습니다.");
		}

		articleService.deleteArticle(id);
		return rq.jsReplace(Ut.f("%d번 글 삭제했습니다.", id), "/usr/article/list");
	}

	@RequestMapping("/usr/article/modify")
	public String showModify(Model model, int id) {

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
	public String doModify(int id, String title, String body) {


		Article article = articleService.getArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return rq.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsHistoryBack("게시물에 대한 권한이 없습니다.");
		}

		articleService.modifyArticle(id, title, body);
		return rq.jsReplace(Ut.f("%d번 게시물이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}

	// 액션메서드끝
}
