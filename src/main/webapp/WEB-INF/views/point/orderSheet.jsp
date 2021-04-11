<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>

<div class="container" style="margin-top:30px">
<div class="row">
<%-- ${orderList } --%>
<div class="col-md-10 offset-md-1">
<h1 class="text-primary text-center">${loginUser.nick }[${loginUser.id }]님의 주문정보</h1>

<table class="table table-responsive">
   <tr>
      <th colspan="5">주문 상품 정보</th>
   </tr>
   <tr bgcolor="#efefef" align="center">
      <td width="10%">상품번호</td>
      <td width="25%">상품명</td>
      <td width="30%">판매가</td>
     <!--  <td width="10%">수 량</td>
      <td width="25%">합계금액</td> -->
   </tr>
      <!-- ------------ 배송비(3000) 감안해서 totalBuy 디폴트값을 3000원으로 설정-->
      <c:set var="totalBuy" value="${p.price }"/> <!-- 총 주문 금액 -->
      
      <c:forEach var="p" items="${orderList}"> <!-- orderList에는 ProductVO가 담겨있음. -->
      <c:set var="totalBuy" value="${totalBuy + p.totalPrice }"/>
      
      <c:set var="totalBuyPoint" value="${totalBuyPoint + p.totalPoint }"/>
      
      <tr>
         <td>${p.pnum }</td>
         <td align="center">
         ${p.pname }<br>
         <a href="../prodDetail?pnum=${p.pnum }" target="_blank"><img
            src="../product_images/${p.pimage }"
            width="100" height="100" border="0" />
         </a>
          </td>
         <td align="right" style="padding-right:20px">
            <fmt:formatNumber value="${p.price }" pattern="###,###"/><br>
          
         </td> 
      <%--  <td align="center">${p.pqty }개</td>
         <td  align="right" style="padding-right:20px">
         <b>
         <fmt:formatNumber value="${p.totalPrice }" pattern="###,###" />
         원<br />
         </b>
         <b>
         [${p.totalPoint }]POINT
         </b>
         </td>  --%>
      </tr>
      </c:forEach>
      <!-- ----------------------- -->

   <tr bgcolor="#efefef">
      <td colspan="2">주문일자<br />
      <b>
      <fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd hh:mm:ss"/>
      </b></td>
      <td colspan="3" align="right">주문총금액<!-- [배송비(3000원)+주문총액] -->: 
      <h2 class="text-danger" id="total">
      <fmt:formatNumber value="${totalBuy }" pattern="###,###" />
      </h2>원<br />
     <%--  적립 포인트 : [<font color=blue><b> ${totalBuyPoint }</b></font>]POINT<br />
 --%>
      </td>
   </tr>
   <!-- --------------------- -->
</table>
<br />
<!-- form 시작=================== -->
<form name="custF" action="orderAdd" method="POST">
<!-- ----------------------------------------------------- -->

<input   type="text" name="ototalPrice" id="ototalPrice" value="${totalBuy}" />
 
<input   type="text" name="ototalPoint" id="ototalPoint" value="${totalBuyPoint}" /> 

<input type="hidden" name="idx_fk" value="${loginUser.idx}">
<!-- hidden으로 주문총액과 총포인트를 넘기자-------------------- -->
<!--  -->
<!-- 마일리지 사용 부가결제금액 -->
      <div id="pointInfo" style="margin-top:20px;">
             <table class="table table-bordered" >         
                <!-- 적립금 -->
                <tbody>
              <%--   <tr>
            <th scope="row" width="150px">사용가능 적립금</th>
                <td style="padding-left:10px;">
                <p> 
                   <input name="omileage" id="omileage" type="text"
                    class="text-right" value="0"
                   size="10" oninput="checkPoint(this, '${mileage}')" >
                    원 (사용가능 적립금 :<span style="color:red;font-weight:bold">${mileage}원</span> 
                    <input type="checkbox" id="chkPoint"
                    onclick="useAllPoint('${mileage}')" >전부사용하기)
                   <input type="button" class="btn btn-success"
                   value="사용하기" 
                   onclick="calcToPrice(omileage.value,${totalBuy})" >            
                </p>                 
                <p>
               적립금은 최소 0 이상일 때 결제가 가능합니다.
                     최대 사용금액은 제한이 없습니다.1회 구매시 적립금 최대 사용금액은 입니다.<br>
                     적립금으로만 결제할 경우, 결제금액이 0으로 보여지는 것은 정상이며
                     <b>[결제하기]</b> 버튼을 누르면 주문이 완료됩니다.<br>
                    적립금 사용 시 배송비는 적립금으로 사용 할 수 없습니다.
                </p>
            </td>
               </tr> --%>
               </tbody>
               </table>            
         </div>


<table class="table table-bordered">
   <tr>
      <th colspan="2">배송지 정보
      <input type="radio" name="info" id="uinfo1" value="1" checked>주문자 정보와 동일
      <input type="radio" name="info" id="uinfo2" value="2">새로운 수령자 정보
      </th>
   </tr>
   <tr>
      <td><b>주문자</b></td>
      <td class="text-left">${loginUser.nick} <%-- [ ${loginUser.id} ] --%></td>
   </tr>
   <tr>
      <td><b>수령자</b></td>
      <td class="text-left">
         <input type="text" name="rcvname" value="${loginUser.nick}">
      </td>
   </tr>
   <tr>
      <td><b>연락처</b></td>
      <td class="text-left">
         <input type="text" name="tel"
            value="${loginUser.tel}"  size="12"
            maxlength="3" />
        </td>
   </tr>
   
  
 
   
   <tr>
      <td><b>배송시 요청사항</b></td>
      <td class="text-left">
      <input type="text" name="omemo"
        class="form-control" /></td>
   </tr>
   <!-- 결제수단 -->
   <tr>
      <td><b>결제방법</b></td>
      <td class="text-left">
         <input type="radio" name="opayway" checked value="100" onclick="showSelect(this.value)">무통장입금
         <input type="radio" name="opayway" value="200"  onclick="showSelect(this.value)">카드 결제
         
         <span id="c1">
            <select name="bank" id="bank">
               <option value="1">국민</option>
               <option value="2">우리</option>
               <option value="3">신한</option>
            </select>
         </span>
         <span id="c2" style="display:none">
            <select name="card" id="card">
               <option value="1">국민카드</option>
               <option value="2">BC카드</option>
               <option value="3">현대카드</option>
               <option value="4">농협카드</option>
            </select>
         </span>
         
      </td>
   </tr>
   
   <tr>
      <td colspan="2"><input type="submit" value="주문결제"
       class="btn btn-success"/></td>
   </tr>
   
</table>

</form>
<!-- ============================ -->
<script type="text/javascript">
   $(function(){
      $('#card').prop("disabled", true);
      $('#bank').prop("disabled", false);
   });
   function checkPoint(input, mileage){
	   var usePoint =parseInt(input.value);
	   if(usePoint>mileage){
		   alert('사용가능한 적립금액 보다 많습니다.\n초기화 합니다');
		   input.value=0;
	   }
     
   }//--------------------------
   function useAllPoint(mileage){
      if($('#chkPoint').is(':checked')){
      	$('#omileage').val(mileage);
      } else{
         $('#omileage').val(0);
      }
   }//---------------------------
   
/*    function calcToPrice(usePoint, totalBuy){
	      // alert(usePoint + "/" + totalBuy)
	      var price = parseInt(totalBuy) - parseInt(usePoint);
	      $('#total').text(price.toLocaleString()); //###,### 효과
	      $('#ototalPrice').val(price);
   } */

   function showSelect(pay){
      //alert(pay);
      
      if(pay=='100'){//무통장 입금
         $('#c1').show();
         $('#c2').hide();
         $('#bank').prop("disabled", false);//은행이 서버로 넘어가도록
         $('#card').prop("disabled", true);
      }else if(pay=='200'){
         //카드결제
         $('#c1').hide();
         $('#c2').show();
         $('#card').prop("disabled", false);//카드가 서버로 넘어가도록
         $('#bank').prop("disabled", true);
      }
   }



   $(function(){
      $('#uinfo1').click(function(){
         custF.nick.value="${loginUser.nick}";
         custF.tel.value="${loginUser.tel}";
        
        
      });
      
      $('#uinfo2').click(function(){
         custF.nick.value="";
         custF.tel.value="";
       
        
      });
   });

   
</script>
</div>
</div>
</div>

<c:import url="/foot"/>