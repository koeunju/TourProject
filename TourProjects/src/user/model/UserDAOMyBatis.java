package user.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    public UserVO loginCheck(String id, String pwd) {

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

    public String checkState(String id) {
        try {
            ses = this.getSessionFactory().openSession();
            return ses.selectOne(NS + ".checkState", id);
        } finally {
            close();
        }
    }

    // 여기부터 admin
    // 유저 list
    public List<UserVO> getUserList() {
        try {
            ses = this.getSessionFactory().openSession();
            List<UserVO> arr = ses.selectList(NS + ".getUserList");
            return arr;
        } finally {
            close();
        }
    }

    // 유저리스트 몇명인지
    public List<UserVO> getUserList(int start, int end) {
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("start", start);
            map.put("end", end);

            ses = this.getSessionFactory().openSession();
            List<UserVO> arr = ses.selectList(NS + ".getUserList", map);
            return arr;
        } finally {
            close();
        }
    }

    // 유저 총 몇명
    public int getUserTotalCount() {
        try {
            ses = this.getSessionFactory().openSession();
            int cnt = ses.selectOne(NS + ".totalCount");
            return cnt;
        } finally {
            close();
        }
    }

    // 유저 정보 삭제
    public int deleteUser(String idx) {
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.delete(NS + ".deleteUser", idx);
            return n;
        } finally {
            close();
        }

    }

    // 유저 정보 수정
    public int updateUser(UserVO user) {
        try {
            ses = this.getSessionFactory().openSession(true);
            int n = ses.update(NS + ".updateUser", user);
            return n;
        } finally {
            close();
        }
    }

    // 유저 정보 수정할때 정보가져오는 메소드
    public UserVO getUser(String idx) {
        try {
            ses = getSessionFactory().openSession();
            UserVO vo = ses.selectOne(NS + ".getUser", idx);
            return vo;
        } finally {
            close();
        }
    }

    // myPage
    public List<UserVO> selectMy(String idx) {
        try {
            ses = this.getSessionFactory().openSession();
            List<UserVO> user = ses.selectList(NS + ".selectMy", idx);
            return user;
        } finally {
            close();
        }
    }

    public UserVO checkPwd(String idx) {
        try {
            ses = this.getSessionFactory().openSession();
            return ses.selectOne(NS + ".checkPwd", idx);
        } finally {
            close();
        }
    }

    public int updateMyInfo(UserVO user) {
        try {
            ses = this.getSessionFactory().openSession(true);
            return ses.update(NS + ".updateMyInfo", user);
        } finally {
            close();
        }
    }
}
