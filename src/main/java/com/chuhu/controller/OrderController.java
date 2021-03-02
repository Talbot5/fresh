package com.chuhu.controller;

import com.chuhu.pojo.*;
import com.chuhu.service.CommodityService;
import com.chuhu.service.OrderService;
import com.chuhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;

    /**
     * 查询订单
     */
    @RequestMapping("selOrderAll")
    @ResponseBody
    public List<Order> selOrderAll(HttpServletResponse response,int uid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Order> orderList=orderService.getselOrderAll(uid);
        for (int i=0;i<orderList.size();i++){
            List<Commodity> commodities=orderService.getselOrderCom(orderList.get(i).getOrid());
            orderList.get(i).setCommodityList(commodities);
        }
        return orderList;
    }

    /**
     *查询不同状态的订单
     */
    @RequestMapping("selOrderStaypay")
    @ResponseBody
    public List<Order> selOrderStaypay(HttpServletResponse response,int uid,int status){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Order> orderConsignment=orderService.getselOrderStaypay(uid,status);
        for (int i=0;i<orderConsignment.size();i++){
            List<Commodity> commodities=orderService.getselOrderCom(orderConsignment.get(i).getOrid());
            orderConsignment.get(i).setCommodityList(commodities);
        }
        return orderConsignment;
    }

    /**
     *查询单个的订单
     */
    @RequestMapping("selOrderDetails")
    @ResponseBody
    public Order selOrderDetails(HttpServletResponse response,int orid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        Order order=orderService.getselOrderDetails(orid);
        System.out.println(order.getOrid());
        List<Commodity> commodities=orderService.getselOrderCom(order.getOrid());
        System.out.println(commodities);
        order.setCommodityList(commodities);
        return order;
    }

    /**
     * 确认收货
     */
    @RequestMapping("upOrderStatus")
    @ResponseBody
    public void upOrderStatus(int orid,int status){
        System.out.println(orid);
        orderService.getupOrderStatus(orid,status);
    }


    /**
     * 下订单---商品详情
     */
    @RequestMapping("placeOrder")
    @ResponseBody
    public PlaceOrder placeOrder(String idlist,int uid){
        PlaceOrder placeOrder =new PlaceOrder();
        List<Commodity> commodityList = new ArrayList<>();
        String[] str=idlist.split(",");
        for (int i=0;i<str.length;i++){
            Commodity commoditydEtails=commodityService.getcommoditydEtails(Integer.parseInt(str[i].toString()));
            commodityList.add(commoditydEtails);
        }
        Address address=orderService.getseladddefault(uid);
        placeOrder.setCommodityList(commodityList);
        placeOrder.setAddress(address);
        return placeOrder;

    }

    /**
     * 添加订单
     */
    @RequestMapping("addOrder")
    @ResponseBody
    public int addOrder(String address,String phone,String username,String payamount,String paytype,int uid,String ids,String nums,String pricelist,Boolean iscoupon,int couponid) throws ParseException {
        System.out.println(pricelist);
        ProfitsPercent profitsPercent=orderService.getSelProfit();
        User user=userService.getSelUser(uid);
        System.out.println(user.toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());
        int paytypeid=0;
        if(paytype.equals("货到付款")){
            paytypeid=1;
        }
        if (iscoupon){
            orderService.getdelCoupon(couponid);
        }
        int count=orderService.getaddOrder(time,address,phone,username,payamount,paytypeid,uid);
        int orderid=0;
        if (count>0){
            orderid=orderService.getselOrder(uid,time);
            int superid=user.getSuperid();
            User superuser=userService.getSelUser(superid);
            String[] idlist=ids.split(",");
            String[] numlist=nums.split(",");
            String[] listprice=pricelist.split(",");
            for (int i=0;i<idlist.length;i++){
                int num=Integer.parseInt(numlist[i].replaceAll("\\[", "").replaceAll("]", ""));
                double price=Double.parseDouble(listprice[i].replaceAll("\\[", "").replaceAll("]", "").replaceAll("\"", ""));
                orderService.getaddorder_commodity(Integer.parseInt(idlist[i].toString()),num,price,orderid);
                orderService.getupcommodiy(Integer.parseInt(idlist[i].toString()));
            }

            String now;
            if(superid>0){
                double money;
                double brforemoney;
                double aftermoney;
                int isagent;
                int ptime;
                money=Double.parseDouble(payamount)*Double.parseDouble(profitsPercent.getUserpercent());
                brforemoney=superuser.getPmoney();
                aftermoney=brforemoney+money;
                isagent=1;
                now=df.format(new Date());
                ptime= (int) (df.parse(now).getTime()/1000);
                orderService.getaddProfits(superid,money,orderid,brforemoney,aftermoney,isagent,ptime);
                ProfitsCount profitsCount=orderService.getselProfitsCount(superid);
                double withdmoney=profitsCount.getWithdmoney();
                double pbalance=aftermoney-withdmoney;
                orderService.getupProfitsCount(superid,aftermoney,pbalance);
                int superagent=user.getSuperagent();
                if (superagent>0) {
                    if (superagent == superid) {
                        System.out.println("您已经拿过佣金了");
                    }else {
                        User agentuser=userService.getSelUser(superagent);
                        System.out.println(agentuser);
                        money=Double.parseDouble(payamount)*Double.parseDouble(profitsPercent.getAgentpercent());
                        brforemoney=agentuser.getPmoney();
                        aftermoney=brforemoney+money;
                        isagent=2;
                        now=df.format(new Date());
                        ptime=(int) (df.parse(now).getTime()/1000);
                        orderService.getaddProfits(superagent,money,orderid,brforemoney,aftermoney,isagent,ptime);
                        orderService.getupProfitsCount(superagent,aftermoney,pbalance);
                    }
                }
            }

        }
        return orderid;
    }


//    /**
//     * 修改销量和库存
//     */
//    @RequestMapping("upcommodiy")
//    @ResponseBody
//    public void upcommodiy(String ids){
//        System.out.println(ids.toString());
//        String[] idlist=ids.split(",");
//        for (int i=0;i<idlist.length;i++){
//            orderService.getupcommodiy(Integer.parseInt(idlist[i].toString()));
//        }
//    }
}
