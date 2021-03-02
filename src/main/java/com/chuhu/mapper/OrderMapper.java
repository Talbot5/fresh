package com.chuhu.mapper;

import com.chuhu.pojo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderMapper {
    @Select("SELECT * FROM order o inner join user u on o.uid=u.uid WHERE o.uid=#{uid} order by ordertime desc")
    List<Order> selOrderAll(int uid);

    @Select("SELECT * FROM order o inner join user u on o.uid=u.uid WHERE o.uid=#{uid} and status=#{status} order by ordertime desc")
    List<Order> selOrderStaypay(int uid, int status);

    @Select("select * from order_commodity o inner join commodity c on o.coid=c.coid where orid=#{orid}")
    List<Commodity> selOrderCom(Integer orid);

    @Select("select * from order  where orid=#{orid}")
    Order selOrderDetails(int orid);

    @Update("update order set status=#{status} where  orid=#{orid}")
    void upOrderStatus(int orid,int status);

    @Select("select * from address where adddefault=1 and uid=#{uid}")
    Address seladddefault(int uid);

    @Insert("insert into order(ordertime,status,address,phone,username,payamount,paytype,uid) value(#{time},1,#{address},#{phone},#{username}," +
            "#{payamount},#{paytype},#{uid})")
    int addOrder(String time, String address,String phone,String username, String payamount, int paytype, Integer uid);

    @Select("select orid from order where uid=#{uid} and ordertime=#{time}")
    int selOrder(int uid,String time);

    @Insert("insert into order_commodity(coid,num,tariff,orid) value(#{id},#{num},#{price},#{orderid})")
    void addorder_commodity(int id, int num,double price, int orderid);

    @Update("update commodity set salenum=salenum+1,stock=stock-1 where coid=#{parseInt}")
    void upcommodiy(int parseInt);

    @Delete("delete from coupon where id=#{couponid}")
    void selCoupon(int couponid);

    @Select("select * from profits_percent")
    ProfitsPercent selProfit();

    @Insert("insert into profits(userid,money,orderid,brforemoney,aftermoney,isagent,ptime) value(#{prouserid},#{money}," +
            "#{orderid},#{brforemoney},#{aftermoney},#{isagent},#{ptime})")
    void addProfits(int prouserid, double money, int orderid, double brforemoney, double aftermoney, int isagent, int ptime);

    @Update("update profits_count set pmoney=#{aftermoney},pbalance=#{pbalance} where  userid=#{prouserid}")
    void upProfitsCount(int prouserid, double aftermoney,double pbalance);

    @Select("select * from profits_count where userid=#{superid}")
    ProfitsCount selProfitsCount(int superid);
}
