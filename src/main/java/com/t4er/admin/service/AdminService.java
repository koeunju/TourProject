package com.t4er.admin.service;

import java.util.List;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductVO;
import com.t4er.user.model.UserVO;

public interface AdminService {

	int getUserCount(AdminPagingVO pvo);

	List<UserVO> listUser(AdminPagingVO pvo);

	int deleteUser(Integer idx);

	int updateUser(UserVO user);

	UserVO getUser(Integer idx);

	int insertProd(ProductVO product);

	ProductVO getProd(Integer pnum);

	int updateProd(ProductVO prod);

	List<OrderVO> listOrder(AdminPagingVO pvo);

	String myTotalPoint2(Integer idx);

	UserVO selectMy2(Integer idx);

}