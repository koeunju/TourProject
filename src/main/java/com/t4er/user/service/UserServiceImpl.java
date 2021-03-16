package com.t4er.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Service("userService")
@Log4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	   @Override
	   public int createUser(UserVO user) {
	      return this.userMapper.createUser(user);
	   }
	   
	   @Override
	   public boolean idCheck(String id) {
	      Integer idx=userMapper.idCheck(id);
	      //아이디로 회원번호 받아오기
	      if(idx==null) {
	         //해당 아이디는 사용 가능
	         return true;
	      }else {
	         //해당 아이디는 사용 중
	         return false;
	      }
	   }

	
	 /**아이디로 회원정보 가져오기*/
	   @Override
	   public UserVO findUser(UserVO findUser) throws NotUserException {
	      UserVO user = this.userMapper.findUser(findUser);
	      if(user==null) {
	         throw new NotUserException("아이디가 존재하지 않아요.");
	      }
	      return user;
	   }
	
	@Override
	   public UserVO loginCheck(String id, String pwd) throws NotUserException {
	      UserVO user = new UserVO();
	      user.setId(id);//      
	      user.setPwd(pwd);
	      
	      UserVO dbUser=this.findUser(user);//db에서 user에 대한 정보를 들고온다.
	      log.info("dbUser=="+dbUser);
	      if(dbUser!=null) {
	         if(dbUser.getPwd().equals(user.getPwd())) {
	            //회원이 맞다면 (비번일치)
	            return dbUser;
	         }
	         
	         //비밀번호 불일치라면 ==> 예외를 발생시키자.
	         throw new NotUserException("비밀번호가 일치하지 않아요!");
	      }
	      return null;
	   }

}
