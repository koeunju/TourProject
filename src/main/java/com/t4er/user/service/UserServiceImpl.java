package com.t4er.user.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserSha256;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Service("userService")
@Log4j
public class UserServiceImpl implements UserService {
	

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
	

	@Override
	public UserVO userCheck(String id, String email) throws NotUserException {
		 UserVO user = new UserVO();
	      user.setId(id);//      
	      user.setEmail(email);
	      
	      UserVO dbUser=this.findUser(user);//db���� user�� ���� ������ ���´�.
	      log.info("dbUser=="+dbUser);
	      if(dbUser!=null) {
	         if(dbUser.getEmail().equals(user.getEmail())) {
	            //ȸ���� �´ٸ� (�����ġ)
	            return dbUser;
	         }
	           
	         throw new NotUserException("�������� �ʴ� �̸��� �Դϴ�.");
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
	
	//���̵� ã��

	public String searchId(String nick, String email) {
		
		String result = "";

		try {
			result = userMapper.searchId(nick, email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	//��й�ȣ ã�� �ӽ� ��й�ȣ ����� �ڵ� 
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
	

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// ������ �̿��� Ű ����
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	public void mailSendPwd(String id, String email, HttpServletRequest req) {
	
		
		String key = getKey(false, 6);
	
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>�ȳ��ϼ��� '"+ id +"' ��</h2><br><br>" 
				+ "<p>�ӽ� �߱� ��й�ȣ�� <h2 style='color : blue'>'" + key +"'</h2>�̸� �α��� �� �������������� ��й�ȣ�� �����Ͻ� �� �ֽ��ϴ�.</p><br>"
				+ "<h3><a href='http://localhost:9090/olan/index'>Ȩ������ ����</a></h3><br><br>";
		try {
			mail.setSubject("�ӽ� ��й�ȣ�� �߱޵Ǿ����ϴ�", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) { 
			e.printStackTrace();
		}
		
		key = UserSha256.encrypt(key);
			
		this.userMapper.searchPwd(id,email,key);


	}


	@Override
	public int searchPwd(String id, String email, String key) {
		// TODO Auto-generated method stub
		return 0;
	}


	}


