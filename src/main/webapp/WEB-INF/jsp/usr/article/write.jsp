<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 작성" />

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="table-box-type-1" method="POST" action="../article/doWrite">
      <input type="hidden" name="id" value="${article.id }" />
      <table>
        <colgroup>
          <col width="200">
        </colgroup>
        <tbody>
          <tr>
            <th>작성자</th>
            <td>${rq.loginedMember.nickname}</td>
          </tr>
           <tr>
           	<th>게시판</th>
           	  <td> 
                <select class="select select-bordered" name="boardId">
                  <option selected disabled>게시판을 선택해주세요</option>
                  <option value="1">공지사항</option>
                  <option value="2">자유게시판1</option>
                </select>
              </td>
           </tr>
          <tr>
            <th>제목</th>
            <td> 
              <input class="w-96 input input-bordered"  name="title" type="text" placeholder="제목" />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="w-full textarea textarea-bordered" name="body" rows="10" placeholder="내용">
              </textarea>
            </td>
          <tr>
            <th>수정</th>
            <td>
              <button type="submit" class="btn btn-outline btn-secondary ml-2">작성</button>
            </td>
          <tr> 
        </tbody>
      </table>
     <button type="button" class="mt-5 btn btn-outline btn-primary" onclick="history.back();">뒤로가기</button>
    </form>
  </div>
</section>
<%@ include file="../common/foot.jspf"%> 