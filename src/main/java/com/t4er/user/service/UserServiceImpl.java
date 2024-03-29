package com.t4er.user.service;

import com.t4er.point.mapper.PointMapper;
import com.t4er.user.exception.NotUserException;
import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.UserVO;

import com.t4er.user.security.UserSha256;
import lombok.extern.log4j.Log4j;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Service("userService")
@Log4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public int createUser(UserVO user) {
        return this.userMapper.createUser(user);
    }

    @Override
    public boolean idCheck(String id) {
        Integer idx = userMapper.idCheck(id);
        //아이디로 회원번호 받아오기
        if (idx == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean emailCheck(String email) {
        Integer idx = userMapper.emailCheck(email);
        if (idx == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nickCheck(String nick) {
        Integer idx = userMapper.nickCheck(nick);
        if (idx == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean telCheck(String tel) {
        Integer idx = userMapper.telCheck(tel);
        if (idx == null) {
            return true;
        } else {
            return false;
        }
    }

    //회원상태 조회
    @Override
    public String checkState(String id) {
        return this.userMapper.checkState(id);
    }

    /**
     * 아이디로 회원정보 가져오기
     */
    @Override
    public UserVO findUser(UserVO findUser) throws NotUserException {
        UserVO user = this.userMapper.findUser(findUser);
        if (user == null) {
            throw new NotUserException("존재하지 않는 아이디입니다.");
        }
        return user;
    }

    @Override
    public UserVO loginCheck(String id, String pwd) throws NotUserException {
        UserVO user = new UserVO();
        user.setId(id);
        user.setPwd(pwd);

        UserVO dbUser = this.findUser(user); // db에서 user에 대한 정보를 들고온다.
        log.info("dbUser==" + dbUser);
        if (dbUser != null) {
            if (dbUser.getPwd().equals(user.getPwd())) {
                // 회원이 맞다면 (비번일치)
                return dbUser;
            }

            // 비밀번호 불일치라면 ==> 예외를 발생시키자.
            throw new NotUserException("비밀번호가 일치하지 않습니다.");
        }
        return null;
    }

    @Override
    public UserVO userCheck(String id, String email) throws NotUserException {
        UserVO user = new UserVO();
        user.setId(id);
        user.setEmail(email);

        UserVO dbUser=this.findUser(user);// db에서 user에 대한 정보를 들고온다.
        log.info("dbUser=="+dbUser);
        if(dbUser!=null) {
            if(dbUser.getEmail().equals(user.getEmail())) {
                return dbUser;
            }

            throw new NotUserException("존재하지 않는 이메일 입니다.");
        }
        return null;
    }

    public void mailSendWithUserKey(String email, String id, HttpServletRequest req) {

        MimeMessage mail = mailSender.createMimeMessage();
        String htmlStr = "<h2>안녕하세요 올랑올랑입니다</h2><br><br>"
                + "<h3>" + id + "님</h3>" + "<p>인증하기 버튼을 누르시면 로그인 하실 수 있습니다 : "
                + "<a href='http://localhost:8080" + req.getContextPath() + "/user/stat_alter?id=" + id + "'>인증하기</a></p>";
        try {
            mail.setSubject("[본인인증] 올랑올랑 가입 인증메일입니다", "utf-8");
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

    // 아이디 찾기
    public String searchId(String nick, String email) {

        String result = "";

        try {
            result = userMapper.searchId(nick, email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 비밀번호 찾기 임시 비밀번호 만드는 코드
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

    // 난수를 이용한 키 생성
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
        String htmlStr = "<h2>안녕하세요 '"+ id +"' 님</h2><br><br>"
                + "<p>임시 발급 비밀번호는 <h2 style='color : blue'>'" + key +"'</h2>이며 로그인 후 마이페이지에서 비밀번호를 변경하실 수 있습니다.</p><br>"
                + "<h3><a href='http://localhost:8080/index'>홈페이지 접속</a></h3><br><br>";
        try {
            mail.setSubject("임시 비밀번호가 발급되었습니다", "utf-8");
            mail.setText(htmlStr, "utf-8", "html");
            mail.addRecipient(RecipientType.TO, new InternetAddress(email));
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        key = UserSha256.encrypt(key);

        this.userMapper.searchPwd(id,email,key);
    }

    //회원가입시 포인트 부여
    @Override
    public int firstPoint(String id) {
        //받은 아이디로 회원번호 검색
        Integer idx = userMapper.findIdx(id);

        return this.pointMapper.firstPoint(idx);
    }

    //관리자페이지
    @Override
    public UserVO getUser(Integer idx) {
        return this.userMapper.getUser(idx);
    }

}
