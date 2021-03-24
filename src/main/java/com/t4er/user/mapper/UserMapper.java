package com.t4er.user.mapper;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.point.model.PointVO;
import com.t4er.user.model.UserVO;

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

    // Adminm MyPage
    UserVO selectMy(Integer idx);

    int updateUser(UserVO user);

    int leaveMember(Integer idx);

    // 내포인트조회
    List<PointVO> mypoint(Integer idx);

    // 초기 회원가입시 포인트 부여
    int setPoint(Integer idx);

    //admin

    //int updateUserAdmin(UserVO user);

    int getUserCount(AdminPagingVO pvo);

    List<UserVO> listUser(AdminPagingVO pvo);

    UserVO getUser(Integer idx);

    int deleteUser(Integer idx);
}
