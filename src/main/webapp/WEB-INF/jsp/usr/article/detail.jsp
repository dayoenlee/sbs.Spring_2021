<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 내용" />

<%@ include file="../common/head.jspf"%>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
  function ArticleDetail__increaseHitCount(){
	const localStorageKey = 'article__' + params.id + '__viewDone';
	
	if(localStorage.getItem(localStorageKey)){
		return;
	}
	localStorage.setItem(localStorageKey,true);
	
  	$.get('../article/doIncreaseHitCountRd',{
  		id : params.id,
  		ajaxMode: 'Y'
  	  }, function(data){
  		 $('.article-detail__hit-count').empty().html(data.data1);
  	  },'json');
  }
  
  $(function() {
	//실전
	  //ArticleDetail__increaseHitCount();
	//임시
	setTimeout(ArticleDetail__increaseHitCount,3000);
  })
  
</script>
 
<section class="mt-5">
  <div class="container mx-auto px-3">
    <div class="table-box-type-1">
      <table>
        <colgroup>
          <col width="200">
        </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>${article.id}</td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>${article.regDate.substring(2, 16)}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.updateDate.substring(2, 16)}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>조회수</th>
            <td>
              <span class= "badge badge-ghost badge-outline article-detail__hit-count">${article.hitCount}</span>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>${article.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>${article.body}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="btns mt-5">
      <button class="btn btn-outline btn-primary"  type="button" onclick="history.back();">뒤로가기</button>
      <c:if test="${article.extra__actorCanModify}">
        <a href="../article/modify?id=${article.id}" class="btn btn-outline btn-secondary ml-2">수정</a>
      </c:if>
      <c:if test="${article.extra__actorCanDelete}">
        <a onclick="if(confirm('글을 삭제하시겠습니까?')==false){return false;}" href="../article/doDelete?id=${article.id}" class="btn btn-outline btn-accent ml-2">삭제</a>
      </c:if>
    </div>
  </div>
</section>
<%@ include file="../common/foot.jspf"%> 