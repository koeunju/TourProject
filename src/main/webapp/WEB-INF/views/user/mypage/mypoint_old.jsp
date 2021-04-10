<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <!-- 포인트 출력 시작 -->
        <table class="table">
            <tr>
                <th>구분번호</th>
                <th>포인트 적립</th>
                <th>포인트 사용</th>
                <th>적립/사용 내역</th>
                <th>적립/사용 일</th>
                <th>총 포인트</th>
            </tr>
            <!-- 포인트 적립내역 출력 -->
            <c:if test="${mypoint  eq null or empty mypoint  }">
                <tr>
                    <td>적립된 포인트가 없습니다.</td>
                </tr>
            </c:if>
            <c:if test="${mypoint ne null and not empty mypoint }">
                <c:forEach var="mypoint" items="${mypoint }" varStatus="status">
                    <tr>
                        <td> ${status.count}</td>
                        <td><c:if
                                test="${mypoint.pcontent eq 400 or empty mypoint.pcontent}">
                            -
                        </c:if> <c:if test="${mypoint.pcontent ne 400 }">
                            <label class="text-primary">${mypoint.psavePoint }</label>
                        </c:if></td>

                        <td><c:if test="${mypoint.pcontent ne 400}">
                            -
                        </c:if> <c:if test="${mypoint.pcontent eq 400 }">
                            <label class="text-danger">${mypoint.psavePoint }</label>
                        </c:if></td>
                        <td><c:if test="${mypoint.pcontent==100 }">
                            <label>회원가입</label>
                        </c:if> <c:if test="${mypoint.pcontent==200 }">
                            <label>게시판 글 작성</label>
                        </c:if> <c:if test="${mypoint.pcontent==300 }">
                            <label>리뷰작성</label>
                        </c:if> <c:if test="${mypoint.pcontent==400 }">
                            <label>포인트샵 사용</label>
                        </c:if> <c:if test="${mypoint.pcontent==500 }">
                            <label>이달의 여행지 선정</label>
                        </c:if></td>

                        <td>${mypoint.pdate }</td>
                        <td>${mypoint.ptotalPoint }</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>