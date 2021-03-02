package com.chuhu.controller;

import com.chuhu.pojo.Activity;
import com.chuhu.pojo.Address;
import com.chuhu.service.ActivityService;
import com.chuhu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private OrderService orderService;

    /**
     *活动分类
     */
    @RequestMapping("activity")
    @ResponseBody
    public List<Activity> activity(String name){
        List<Activity> activityList=activityService.getselactivity(name);
        return activityList;
    }

    /**
     * 活动商品详情
     */
    @RequestMapping("activityDetails")
    @ResponseBody
    public Activity activityDetails(int coid){
        Activity activity=activityService.getselactivityDetails(coid);
        return activity;
    }

    /**
     * 活动商品--下单
     */
    @RequestMapping("activityOrder")
    @ResponseBody
    public Activity activityOrder(int coid,int uid){
        Activity activity=activityService.getselactivityDetails(coid);
        Address address=orderService.getseladddefault(uid);
        activity.setAddress(address);
        return activity;
    }
}
