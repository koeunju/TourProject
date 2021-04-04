package com.t4er.user.mapper;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    Integer idCheck(String id);
    Integer emailCheck(String email);
    Integer nickCheck(String nick);
    Integer telCheck(String tel);

    UserVO findUser(UserVO findUser);

    int createUser(UserVO user);

    String checkState(String id);

    int statAlter(String id);

    String searchId(@Param("nick")String nick, @Param("email")String email);

    int searchPwd(String id, String email, String key);

    // Adminm MyPage
    UserVO selectMy(Integer idx);
    int updateUser(UserVO user);
    int leaveMember(Integer idx);
    //아이디로 회원번호 조회
    Integer findIdx(String id);

    //admin
    //int updateUserAdmin(UserVO user);
    int getUserCount(AdminPagingVO pvo);
    List<UserVO> listUser(AdminPagingVO pvo);
    UserVO getUser(Integer idx);
    int deleteUser(Integer idx);
}
