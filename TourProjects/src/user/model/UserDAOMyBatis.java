package user.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.model.DAOMyBatisBase;

public class UserDAOMyBatis extends DAOMyBatisBase {

    // 네임스페이스 SamlpeMapper.xml에 기술된 네임스페이스 속성값을 갖도록
    private final String NS = "common.mapper.UserMapper";

    public int createUser(UserVO vo) {
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.insert(NS + ".createUser", vo);
            return n;
        } finally {
            close();
        }
    }

    public int idCheck(String id) {
        try {
            ses = this.getSessionFactory().openSession();
            int b = ses.selectOne(NS + ".idCheck", id);
            return b;
        } finally {
            close();
        }
    }

    public UserVO loginCheck(String id, String pwd) throws NotUserException {
        UserVO user = login(id, pwd);
        if (user == null) {
            //아이디가 존재하지 않는 경우 => 예외 발생
            throw new NotUserException(id + "란 아이디는 존재하지 않아요");
        }
        //비밀번호 일치 여부 체크
        String dbPwd = user.getPwd();
        if (!pwd.equals(dbPwd)) {
            throw new NotUserException("비밀번호가 일치하지 않아요");
        }
        return user;//아이디와 비번이 일치한 경우 user반환
    }

    public UserVO login(String id, String pwd) {
        try {
            ses = this.getSessionFactory().openSession();
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("pwd", pwd);
            return ses.selectOne(NS + ".loginCheck", map);
        } finally {
            close();
        }
    }
}
