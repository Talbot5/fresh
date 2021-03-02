package com.chuhu.service;

import com.chuhu.pojo.Code;
import com.chuhu.pojo.Coupon;
import com.chuhu.pojo.User;

import java.util.List;

public interface UserService {
    User getjudgeUser(String openid);

    int getaddUser(String nickName, String head, String openid,int shareid,int superagent);

    User getSelUser(int uid);

    int getaddCoupon(int uid,String starttime,String stoptime);

    void getupUser(int uid);

    List<Coupon> getselCoupon(int uid);

    List<Coupon> getselCouponNum(int uid, String num);

    void getaddNews(String time,Integer shareid);

    User getSelOneUser(int uid);

    List<User> getSelSuperUser(int superid);

    Code getselCode();

    Code getselPoster();

    int getselNewUser(int uid);

    int getselProfitsCount(Integer uid);

    void getaddProfitsCount(Integer uid);

    int getupProfitsCount(Integer shareid);

    void getaddWithdrawal(int uid, double withdmoney, double brforewmoney, String account, int type, int wtime);

    User getselUserMember(Integer uid);
}
