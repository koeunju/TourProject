package com.t4er.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Service("userService")
@Log4j
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JavaMailSender mailSender;

	
	   @Override
	   public int createUser(UserVO user) {
	      return this.userMapper.createUser(user);
	   }
	   
	   @Override
	   public boolean idCheck(String id) {
	      Integer idx=userMapper.idCheck(id);
	      //���̵�� ȸ����ȣ �޾ƿ���
	      if(idx==null) {
	         return true;
	      }else {
	         return false;
	      }
	   }
	   
	   public boolean emailCheck(String email) {
		   Integer idx=userMapper.emailCheck(email);
		   if(idx==null) {
		        return true;
		   }else {
	          return false;
		      }
	   }
	   
	   public boolean nickCheck(String nick) {
		   Integer idx=userMapper.nickCheck(nick);
		   if(idx==null) {
		        return true;
		   }else {
	          return false;
		      }
	   }
	   
	   public boolean telCheck(String tel) {
		   Integer idx=userMapper.telCheck(tel);
		   if(idx==null) {
		        return true;
		   }else {
	          return false;
		      }
	   }
	   
		//ȸ������ ��ȸ
		@Override
		public String checkState(String id) {
			return this.userMapper.checkState(id);
		}

	
	 /**���̵�� ȸ������ ��������*/
	   @Override
	   public UserVO findUser(UserVO findUser) throws NotUserException {
	      UserVO user = this.userMapper.findUser(findUser);
	      if(user==null) {
	         throw new NotUserException("�������� �ʴ� ���̵��Դϴ�."); 
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
	         throw new NotUserException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	      }
	      return null;
	   }
	

	

	public void mailSendWithUserKey(String email, String id, HttpServletRequest req) {
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>�ȳ��ϼ��� �ö��ö��Դϴ�</h2><br><br>" 
				+ "<h3>" + id + "��</h3>" + "<p>�����ϱ� ��ư�� �����ø� �α��� �Ͻ� �� �ֽ��ϴ� : "
				+ "<a href='http://localhost:9090" + req.getContextPath() + "/user/stat_alter?id="+ id +"'>�����ϱ�</a></p>";
		try {
		
			mail.setSubject("[��������] �ö��ö� ���� ���������Դϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(mail);		
	}
	
	
	@Override
	public int statAlter(String id) {
		return this.userMapper.statAlter(id);
		
	}

 	

}