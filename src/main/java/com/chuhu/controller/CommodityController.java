package com.chuhu.controller;

import com.chuhu.pojo.*;
import com.chuhu.service.CartService;
import com.chuhu.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 商品查询
 */
@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CartService cartService;



    /**
     *查询所有商品
     */
    @RequestMapping("commodityAll")
    @ResponseBody
    public  List<Commodity> commodityAll(HttpServletResponse response, int uid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Commodity> commodityList= commodityService.getCommodityAll();
        if (uid>0){
            List<CommoditCart> cartlist = cartService.getCartAll(uid);
            for (int i = 0; i < commodityList.size(); i++) {
                for (int j = 0; j < cartlist.size(); j++) {
                    if (commodityList.get(i).getCoid() == cartlist.get(j).getCoid()) {
                        commodityList.get(i).setCartnum(cartlist.get(j).getNum());
                        commodityList.get(i).setCartid(cartlist.get(j).getCid());
                    }
                }
            }
        }
        return commodityList;
    }

    /**
     *
     *搜索查询
     */
    @RequestMapping("searchCommodity")
    @ResponseBody
    public List<Commodity> searchCommodity( HttpServletResponse response,String name,int uid){
        System.out.println(name);
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Commodity> searchCommodityList= commodityService.getSearchCommodity(name);
        if(uid>0) {
            List<CommoditCart> cartlist = cartService.getCartAll(uid);
            for (int i = 0; i < searchCommodityList.size(); i++) {
                for (int j = 0; j < cartlist.size(); j++) {
                    if (searchCommodityList.get(i).getCoid() == cartlist.get(j).getCoid()) {
                        searchCommodityList.get(i).setCartnum(cartlist.get(j).getNum());
                        searchCommodityList.get(i).setCartid(cartlist.get(j).getCid());
                    }
                }
            }
        }
        return searchCommodityList;
    }

    /**
     * 商品分类---商品
     */
    @RequestMapping("commodityTypeAll")
    @ResponseBody
    public List commodityTypeAll(HttpServletResponse response,Integer uid){
        if (uid == null) uid = 0;
        System.out.println(uid);
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Comtype> type=commodityService.getCommodityTypeAllName();
        List list=new ArrayList();
        for (int i=0;i<type.size();i++){
            Map commodityTypeMap =new HashMap();
            String typename=type.get(i).getTypename();
            List<Comtype> commodityTypeAll=commodityService.getCommodityTypeAll(typename);
            if (uid>0){
                List<CommoditCart> cartlist=cartService.getCartAll(uid);
                for (int a=0;a<commodityTypeAll.size();a++) {
                    for (int j = 0; j < cartlist.size(); j++) {
                        if (commodityTypeAll.get(a).getCoid() == cartlist.get(j).getCoid()) {
                            commodityTypeAll.get(a).setCartnum(cartlist.get(j).getNum());
                            commodityTypeAll.get(a).setCartid(cartlist.get(j).getCid());
                        }
                    }
                }
            }
            commodityTypeMap.put("id",type.get(i).getTypeid());
            commodityTypeMap.put("name",type.get(i).getTypename());
            commodityTypeMap.put("list",commodityTypeAll);
            list.add(commodityTypeMap);
        }
        return list;
    }


    /**
     * 商品分类---类别
     */
    @RequestMapping("commodityType")
    @ResponseBody
    public List<Comtype> commodityType(HttpServletResponse response){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Comtype> commodityType=commodityService.getCommodityType();
        return commodityType;
    }

    /**
     * 商品分类--单个类别
     */
    @RequestMapping("commodityOneType")
    @ResponseBody
    public List<Comtype> commodityOneType(HttpServletResponse response,String name,int uid){
        System.out.println(name);
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Comtype> commodityType=commodityService.getCommodityTypeAll(name);
        if(uid>0){
            List<CommoditCart> cartlist = cartService.getCartAll(uid);
            for (int a = 0; a < commodityType.size(); a++) {
                for (int j = 0; j < cartlist.size(); j++) {
                    if (commodityType.get(a).getCoid() == cartlist.get(j).getCoid()) {
                        commodityType.get(a).setCartnum(cartlist.get(j).getNum());
                        commodityType.get(a).setCartid(cartlist.get(j).getCid());
                    }
                }
            }
        }
        return commodityType;
    }

    /**
     * 商品详情
     */
    @RequestMapping("commoditydEtails")
    @ResponseBody
    public Commodity commoditydEtails(HttpServletResponse response,int coid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        Commodity commoditydEtails=commodityService.getcommoditydEtails(coid);
        return commoditydEtails;
    }

    /**
     * 查询轮播gaung
     */
    @RequestMapping("advertPhoto")
    @ResponseBody
    public List<Advertisement> advertPhoto(){
        List<Advertisement> advertisementList=commodityService.getadvertPhoto();
        return advertisementList;
    }

}
