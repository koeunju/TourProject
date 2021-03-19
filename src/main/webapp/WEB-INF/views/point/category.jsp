<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-tabs mb-5">
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/point">전체목록</a>
    </li>

    <c:forEach var="up" items="${cList}" varStatus="st">
        <li class="nav-tabs">
            <a class="nav-link active"
               href="productByCate?cgnum=${up.getCg_num()}">
                    ${up.getCg_name()}
            </a>
        </li>
    </c:forEach>

</ul>

<script type="text/javascript">
    function showItem(cgnum) {
        //location.href = "${pageContext.request.contextPath}/productByCate?cgnum="+cgnum;
        location.href = "${pageContext.request.contextPath}/productByCate?pageSize="+pageSize+"&findKeyword="+findKeyword+"&cgnum="+cgnum;
    }
</script>    