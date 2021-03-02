package com.chuhu.service.impl;

import com.chuhu.mapper.UserMapper;
import com.chuhu.pojo.Code;
import com.chuhu.pojo.Coupon;
import com.chuhu.pojo.User;
import com.chuhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getjudgeUser(String openid) {
        return userMapper.judgeUser(openid);
    }

    @Override
    public int getaddUser(String nickName, String head, String openid,int shareid,int superagent) {
        return userMapper.addUser(nickName,head,openid,shareid,superagent);
    }

    @Override
    public User getSelUser(int uid) {
        return userMapper.selUser(uid);
    }

    @Override
    public int getaddCoupon(int uid,String starttime,String stoptime) {
        return userMapper.addCoupon(uid,starttime,stoptime);
    }

    @Override
    public void getupUser(int uid) {
        userMapper.upUser(uid);
    }

    @Override
    public List<Coupon> getselCoupon(int uid) {
        return userMapper.selCoupon(uid);
    }

    @Override
    public List<Coupon> getselCouponNum(int uid, String num) {
        return userMapper.selCouponNum(uid,num);
    }

    @Override
    public int getupProfitsCount(Integer shareid) {
       return userMapper.upProfitsCount(shareid);
    }

    @Override
    public void getaddWithdrawal(int uid, double withdmoney, double brforewmoney, String account, int type, int wtime) {
        userMapper.addWithdrawal(uid,withdmoney,brforewmoney,account,type,wtime);
    }

    @Override
    public User getselUserMember(Integer uid) {
        return userMapper.selUserMember(uid);
    }

    @Override
    public void getaddNews(String time,Integer shareid) {
        userMapper.addNews(time,shareid);
    }

    @Override
    public User getSelOneUser(int uid) {
        return userMapper.selOneUser(uid);
    }

    @Override
    public List<User> getSelSuperUser(int superid) {
        return userMapper.selSuperUser(superid);
    }

    @Override
    public Code getselCode() {
        return userMapper.selCode();
    }

    @Override
    public Code getselPoster() {
        return userMapper.selPoster();
    }

    @Override
    public int getselNewUser(int uid) {
        return userMapper.selNewUser(uid);
    }

    @Override
    public int getselProfitsCount(Integer uid) {
        return userMapper.selProfitsCount(uid);
    }

    @Override
    public void getaddProfitsCount(Integer uid) {
        userMapper.addProfitsCount(uid);
    }
}
