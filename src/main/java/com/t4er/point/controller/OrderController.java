
package com.t4er.point.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.t4er.point.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.t4er.common.CommonUtil;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.service.OrderService;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
public class OrderController {
   
   @Autowired
   private ProductService productService;
   
   @Autowired
   private OrderService orderService;
   
   @Autowired
   private UserService userSerivce;
   
   @Autowired
   private CommonUtil util;
   
   @RequestMapping("/order")
   public String orderForm(Model m, HttpSession ses, @RequestParam("pnum") int[] pnum) {
      log.info("pnum[0]="+pnum[0]);
		/* log.info("oqty[0]="+oqty[0]); */
      List<ProductVO> orderList=new ArrayList<>();
      //주문한 상품정보를 DB에서 가져오기
      if(pnum!=null ) {
         for(int i=0;i<pnum.length;i++) {
            int pn=pnum[i];//상품번호
            ProductVO item = productService.selectByPnum(pn);
            //상품의 수량을 주문수량으로 설정
			/* item.setPqty(oqty); */
            orderList.add(item);            
         }//for-----------
      }//if-------------------------------
      m.addAttribute("orderList",orderList);
      
      ses.setAttribute("orderList", orderList);
      
      //주문자의 마일리지 가져오기
      UserVO loginUser=(UserVO)ses.getAttribute("loginUser");
      
      UserVO dbUser=this.userSerivce.getUser(loginUser.getIdx());
      if(dbUser!=null) {
         ses.setAttribute("point", dbUser.getPoint());
      }
      //주문한 상품정보를 세션에 저장하자.
      ses.setAttribute("orderList", orderList);
      
      return "point/orderSheet";
   }//-----------------------------------------
   
   /*주문처리*/
   @PostMapping("/orderAdd")
   public String orderAdd(Model m, HttpSession ses,
         @ModelAttribute("ovo")OrderVO ovo)
         
         //@ModelAttribute("receiver") ReceiverVO receiver)
         {
      log.info("ovo===="+ovo);//주문 개요 정보
      //log.info("receiver=="+receiver);//수령자 정보
      //주문상품 정보==> 세션에서 꺼내야 함
      //1. 주문한 상품정보 가져오기
      List<ProductVO> orderList =(List<ProductVO>)ses.getAttribute("orderList");
      
      //2. 주문한 상품정보와 수령자 정보를 ovo에 setting=> setter를 이용해서
      ovo.setOrderList(orderList);
      //ovo.setReceiver(receiver);
      
      //3. 결제 방법에 따라서 결제상태값 결정
      //   카드결제(200) 면=> opaystate =>'결제완료'
      //   무통장(100)  면=> opaystate =>'미결제'
    
      if(ovo.getOpayway().equals("200")) {
         ovo.setOpaystate("결제완료");
      }else if(ovo.getOpayway().equals("100")) {
         ovo.setOpaystate("미결제");
      }
      ovo.setOdeliverstate("미배송");
      
      log.info("ovo>>>>>"+ovo);
    
      
      //4. 주문정보를 DB에 insert한다
      String onum = orderService.orderInsert(ovo);
      
      //5. 주문번호=> 세션에 저장
      ses.setAttribute("onum", onum);
      //6. 회원정보를 db에서 다시 가져와서 세션에 저장
      // =>주문하면 mileage(적립금) 변동이 있으므로   
      UserVO dbUser=this.userSerivce.getUser(ovo.getIdx_fk());
      if(dbUser!=null) {
         int mileage=dbUser.getPoint();
         ses.setAttribute("mileage", mileage);
      }
      
      //7. 주문처리가 완료되면 ==> 장바구니 목록에서 주문한 상품정보를 삭제한다.(회원번호, 상품번호)
      if(orderList!=null) {
         for(ProductVO prod:orderList) {
            int idx_fk=ovo.getIdx_fk();
            int pnum=Integer.parseInt(prod.getPnum());
            this.productService.delCartByOrder(idx_fk, pnum);//장바구니에서 해당 상품 삭제
         }
      }
      return "redirect:orderDetail?onum="+onum;
      //8. 주문 상세 내역 페이지로 이동 (redirect이동해야 함)
      //    새로고침시 이중 주문이 발생할 수 있으므로 forward가 아니라 redirect로 이동
   }//-----------------------------------------
   
   @GetMapping("/orderDetail")
   public String orderDetail(Model m,HttpSession ses, @RequestParam(defaultValue = "") String onum) {
      if(onum.isEmpty()) {
         onum = (String)ses.getAttribute("onum");
         if(onum==null) {
            return util.addMsgBack(m, "잘못 들어온 경로입니다");
         }
      }
      //주문번호로 상세 주문내역 가져오기
      List<OrderVO> orderDesc = this.orderService.getOrderDesc(onum);
      m.addAttribute("orderDesc", orderDesc);
      m.addAttribute("onum", onum);
      
      return "point/orderDesc";
   }//-----------------------------------------
   
 
   
}/////////////////////////////////////////////////////





