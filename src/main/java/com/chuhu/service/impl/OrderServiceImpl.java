package com.chuhu.service.impl;

import com.chuhu.mapper.OrderMapper;
import com.chuhu.pojo.*;
import com.chuhu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getselOrderAll(int uid) {
        return orderMapper.selOrderAll(uid);
    }

    @Override
    public List<Order> getselOrderStaypay(int uid, int status) {
        return orderMapper.selOrderStaypay(uid,status);
    }

    @Override
    public List<Commodity> getselOrderCom(Integer orid) {
        return orderMapper.selOrderCom(orid);
    }

    @Override
    public Order getselOrderDetails(int orid) {
        return orderMapper.selOrderDetails(orid);
    }

    @Override
    public void getupOrderStatus(int orid,int status) {
        orderMapper.upOrderStatus(orid,status);
    }

    @Override
    public Address getseladddefault(int uid) {
        return orderMapper.seladddefault(uid);
    }

    @Override
    public int getaddOrder(String time, String address,String phone,String username, String payamount, int paytype, Integer uid) {
        return orderMapper.addOrder(time,address,phone,username,payamount,paytype,uid);
    }

    @Override
    public int getselOrder(int uid,String time) {
        return orderMapper.selOrder(uid,time);
    }

    @Override
    public void getaddorder_commodity(int id, int num,double price, int orderid) {
         orderMapper.addorder_commodity(id,num,price,orderid);
    }

    @Override
    public void getupcommodiy(int parseInt) {
        orderMapper.upcommodiy(parseInt);
    }

    @Override
    public void getdelCoupon(int couponid) {
        orderMapper.selCoupon(couponid);
    }

    @Override
    public ProfitsPercent getSelProfit() {
        return orderMapper.selProfit();
    }

    @Override
    public void getaddProfits(int prouserid, double money, int orderid, double brforemoney, double aftermoney, int isagent, int ptime) {
        orderMapper.addProfits(prouserid,money,orderid,brforemoney,aftermoney,isagent,ptime);
    }

    @Override
    public void getupProfitsCount(int prouserid, double aftermoney,double pbalance) {
        orderMapper.upProfitsCount(prouserid,aftermoney,pbalance);
    }

    @Override
    public ProfitsCount getselProfitsCount(int superid) {
        return orderMapper.selProfitsCount(superid);
    }


}
