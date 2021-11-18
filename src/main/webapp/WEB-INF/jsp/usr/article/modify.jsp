<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 수정" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../article/doModify">
      <input type="hidden" name="id" value="${article.id }" />
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
            <td>${article.regDateForPrint}</td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>${article.updateDateForPrint}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>${article.extra__writerName}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td> 
              <textarea class="w-96 textarea h-24 textarea-bordered textarea-info" name="title" type="text" rows="10">
              ${article.title }</textarea>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="w-full textarea h-24 textarea-bordered textarea-info" name="body" rows="10">
              ${article.body}</textarea>
            </td>
          <tr>
            <th>수정</th>
            <td>
              <button type="submit" class="btn btn-outline btn-secondary ml-2">수정</button>
              <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
              <c:if test="${article.extra__actorCanDelete}">
                <a onclick="if(confirm('글을 삭제하시겠습니까?')==false){return false;}" href="../article/doDelete?id=${article.id}" class="btn btn-outline btn-accent ml-2">삭제</a>
              </c:if>
            </td>
          <tr> 
        </tbody>
      </table>
    </form>
  </div>
</section>
<%@ include file="../common/foot.jspf"%> 