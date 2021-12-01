package com.sbs.exam.demo.repository;


import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Mapper
public interface ArticleRepository {
//interface 추상클래스

	public void writeArticle(@Param("memberId")int memberId,@Param("boardId")int boardId,@Param("title") String title,@Param("body") String body);
	

	public  Article getArticle(@Param("id") int id);
	

	public  void deleteArticle(int id);
	

	public void modifyArticle(@Param("id") int id,@Param("title")String title,@Param("body") String body);
	

	public List<Article> getArticles(int boardId,String searchKeywordTypeCode, String searchKeyword,int limitStart, int limitTake);
	

	public int getLastInsertId();


	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	@Update("""
				<script>
				UPDATE article
				SET	hitCount = hitCount + 1
				WHERE id = #{id}
				</script>
			""")
	public int increaseHitCount(int id);
	@Select("""
				<script>
				SELECT hitCount
				FROM article
				WHERE id = #{id}
				</script>
			""")
	public int getArticleHitCount(int id);
}
