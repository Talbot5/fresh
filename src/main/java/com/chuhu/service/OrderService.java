package com.chuhu.service;

import com.chuhu.pojo.*;

import java.util.List;

public interface OrderService {
    List<Order> getselOrderAll(int uid);

    List<Order> getselOrderStaypay(int uid, int status);

    List<Commodity> getselOrderCom(Integer orid);

    Order getselOrderDetails(int orid);

    void getupOrderStatus(int orid,int status);

    Address getseladddefault(int uid);

    int getaddOrder(String time, String address,String phone,String username, String payamount, int paytype, Integer uid);

    int getselOrder(int uid,String time);

    void getaddorder_commodity(int id, int num,double price, int orderid);

    void getupcommodiy(int parseInt);

    void getdelCoupon(int couponid);

    ProfitsPercent getSelProfit();

    void getaddProfits(int prouserid, double money, int orderid, double brforemoney, double aftermoney, int isagent, int ptime);

    void getupProfitsCount(int superid, double aftermoney,double pbalance);

    ProfitsCount getselProfitsCount(int superid);
}
