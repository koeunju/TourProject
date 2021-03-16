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
	      //���̵�� ȸ����ȣ �޾ƿ���
	      if(idx==null) {
	         //�ش� ���̵�� ��� ����
	         return true;
	      }else {
	         //�ش� ���̵�� ��� ��
	         return false;
	      }
	   }

	
	 /**���̵�� ȸ������ ��������*/
	   @Override
	   public UserVO findUser(UserVO findUser) throws NotUserException {
	      UserVO user = this.userMapper.findUser(findUser);
	      if(user==null) {
	         throw new NotUserException("���̵� �������� �ʾƿ�.");
	      }
	      return user;
	   }
	
	@Override
	   public UserVO loginCheck(String id, String pwd) throws NotUserException {
	      UserVO user = new UserVO();
	      user.setId(id);//      
	      user.setPwd(pwd);
	      
	      UserVO dbUser=this.findUser(user);//db���� user�� ���� ������ ���´�.
	      log.info("dbUser=="+dbUser);
	      if(dbUser!=null) {
	         if(dbUser.getPwd().equals(user.getPwd())) {
	            //ȸ���� �´ٸ� (�����ġ)
	            return dbUser;
	         }
	         
	         //��й�ȣ ����ġ��� ==> ���ܸ� �߻���Ű��.
	         throw new NotUserException("��й�ȣ�� ��ġ���� �ʾƿ�!");
	      }
	      return null;
	   }

}
