<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/top.jsp" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="wrap" align="center">
			
		<h2>고객센터</h2>

		<!-- 검색폼 -->
		<div class="col-md-12">
			<form name="findF" id="findF" action="boardFind.do#bbs"
				class="form-inline">
				<select name="findType" id="findType" class="form-control m-3">
					<option value="0">::검색 유형::</option>
					<option value="1">제 목</option>
					<option value="2">작성자</option>
					<option value="3">글내용</option>
				</select> <input type="text" name="findKeyword" id="findKeyword"
					placeholder="검색어를 입력하세요" class="form-control m-3" required>
				<button class="btn btn-primary">검 색</button>
			</form>
		</div>

		<!-- 분류 -->
		<table class="table table-striped table-hover" id="bbs">
			<tr class="table-secondary">
				<th width="10%">글번호</th>
				<th width="30%">제 목</th>
				<th width="20%">글쓴이</th>
				<th width="20%">날 짜</th>
				<th width="10%">조회수</th>
				<th width="10%">답변여부</th> <!-- 스프링에서 해볼까나 -->
			</tr>

			<!-- 데이터넣기 -->
			<c:forEach var="board" items="${boardList}">
				<tr class="record">
					<td>${board.bnum }</td>
					<td style="text-align: left; padding-left: 10px"><a
						href="boardView.do?bnum=<c:out value="${board.bnum}"/>">
							${board.btitle }</a></td>
					<td>${board.idx }</td>
					<td>${board.bdate }</td>
					<td>${board.binquiry }</td>
					<td></td>
				</tr>
			</c:forEach>
			
			<!-- 페이징 시작 -->
			<tr>
				<td colspan="3" class="text-center">
			         <ul class="pagination justify-content-center">
			         <c:if test="${prevBlock>0}">   
			         <li class="page-item">
			            <a class="page-link" href="boardList2.do?cpage=${prevBlock}#bbs">
			            Prev ${pagingBlock}개</a>
			         </li>
			         </c:if>         
			         <!-- 페이지블럭 처리---------------------------- -->
			            
			            <c:forEach var="i" begin="${prevBlock+1}" end="${nextBlock-1}" step="1">
			               <%-- [ ${i} ] --%>
			               <c:if test="${i<pageCount+1 }">
			               <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
			                  <a class="page-link"
			                   href="boardList2.do?cpage=${i}&pageSize=${pageSize}#bbs">
			                     ${i}
			                  </a>
			               </li>
			               </c:if>
			            </c:forEach>
			         <!--  ------------------------------------------->
			         <c:if test="${nextBlock <pageCount+1}">  
			         <li class="page-item">
			            <a class="page-link" href="boardList2.do?cpage=${nextBlock}#bbs">
			            Next ${pagingBlock}개</a>
			         </li>
			         </c:if>
			         
			         </ul>
		         </td>
			
				<!-- 총게시물 -->
			 	<td colspan="2">
			         <span class="text-primary">총게시글수: <c:out value="${totalCount}"/>개</span>
			         <br>
			         
	         	</td>
			</tr>
			
			<!-- 함수 -->
			<script type="text/javascript">
			   $(function(){
			      $('#findF').on('submit', function(){      
			         var $type=$('#findType');
			         var $keyword=$('#findKeyword');
			         if($type.val()==0){
			            alert('검색 유형을 선택하세요');
			            $type.focus();
			            return false;
			         }
			         if(!$keyword.val()){
			            alert('검색어를 입력하세요');
			            $keyword.focus();
			            return false;
			         }
			         return true;
			      })
			   })
			</script>
			
			
		</table>
	</div>
</body>


<tr>
	<td align="center" colspan="5">
		<button class="btn btn-primary"
			onclick="location.href='boardInsert2.do'">글쓰기</button>
	</td>
</tr>
</html>

<jsp:include page="/foot.jsp" />