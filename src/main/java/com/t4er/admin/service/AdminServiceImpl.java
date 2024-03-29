package com.t4er.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.point.mapper.OrderMapper;
import com.t4er.point.mapper.PointMapper;
import com.t4er.point.mapper.ProductMapper;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductVO;
import com.t4er.user.mapper.UserMapper;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int getUserCount(AdminPagingVO pvo) {
        return this.userMapper.getUserCount(pvo);
    }

    @Override
    public List<UserVO> listUser(AdminPagingVO pvo) {
        return this.userMapper.listUser(pvo);
    }

    @Override
    public int deleteUser(Integer idx) {
        return this.userMapper.deleteUser(idx);
    }

    @Override
    public int updateUser(UserVO user) {
        return this.userMapper.updateUser(user);
    }

    @Override
    public UserVO getUser(Integer idx) {
        return this.userMapper.getUser(idx);

    }

    //여기부터 상품
    @Override
    public int insertProd(ProductVO product) {
        return this.productMapper.insertProd(product);
    }

    @Override
    public ProductVO getProd(Integer pnum) {
        return this.productMapper.getProd(pnum);
    }

    @Override
    public int updateProd(ProductVO prod) {
        return this.productMapper.updateProd(prod);
    }

    @Override
    public List<OrderVO> listOrder(AdminPagingVO pvo) {
        return this.orderMapper.listOrder(pvo);
    }

    @Override
    public String myTotalPoint2(Integer idx) {
        return this.pointMapper.myTotalPoint(idx);
    }

    @Override
    public UserVO selectMy2(Integer idx) {
        return this.userMapper.selectMy(idx);
    }




}